package com.metacoding.planitapiserver.user;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.metacoding.planitapiserver._core.auth.JwtEnum;
import com.metacoding.planitapiserver._core.auth.JwtUtil;
import com.metacoding.planitapiserver._core.auth.PasswordUtil;
import com.metacoding.planitapiserver._core.error.exception.Exception400;
import com.metacoding.planitapiserver._core.error.exception.Exception401;
import com.metacoding.planitapiserver._core.error.exception.Exception404;
import com.metacoding.planitapiserver._core.util.MyFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO requestDTO) {

        Optional<User> userOP = userRepository.findByUsername(requestDTO.getUsername());
        if(userOP.isPresent()){
            throw new Exception400("유저네임 중복");
        }

        // 1. 비밀번호 암호화
        String encPassword = PasswordUtil.encode(requestDTO.getPassword());
        // 2. base64 -> file 저장
        String imgUrl = null;
        try{
            imgUrl = MyFileUtil.write(requestDTO.getImgBase64());
        }catch (Exception e){
            imgUrl = "/images/1.png";
        }
        // 3. file 경로 가져와서 유저정보 + 사진경로 DB 저장
        User userPS = userRepository.save(requestDTO.toEntity(encPassword, imgUrl));
        return new UserResponse.DTO(userPS);


    }

    // 엑세스토큰, 리플래시토큰을 돌려줘야 한다.
    public UserResponse.LoginDTO 로그인(UserRequest.LoginDTO requestDTO) {
        // 1. 유저 인증
        User userPS = userRepository.findByUsername(requestDTO.getUsername()).orElseThrow(
                ()-> new Exception401("유저네임을 찾을 수 없습니다")
        );
        if(!PasswordUtil.verify(requestDTO.getPassword(), userPS.getPassword())) throw new Exception401("패스워드가 일치하지 않습니다");

        // 3. jwt 생성
        String accessToken = JwtUtil.createdAccessToken(userPS);

        accessToken = "Bearer "+accessToken;

        System.out.println("accessToken : "+accessToken);

        return new UserResponse.LoginDTO(accessToken, userPS);
    }

    // 토큰을 돌려줄 필요가 없다.
    public UserResponse.AutoLoginDTO  자동로그인(String accessToken) {
        Optional.ofNullable(accessToken).orElseThrow(() -> new Exception401(JwtEnum.ACCESS_TOKEN_NOT_FOUND.name()));
        try {
            User user = JwtUtil.verify(accessToken);
            User userPS = userRepository.findByUsername(user.getUsername()).orElseThrow(
                    ()-> new Exception401("유저네임을 찾을 수 없습니다")
            );
            return new UserResponse.AutoLoginDTO(userPS);
        }catch (SignatureVerificationException | JWTDecodeException e1) {
            throw new Exception401(JwtEnum.ACCESS_TOKEN_INVALID.name());
        } catch (TokenExpiredException e2){
            throw new Exception401(JwtEnum.ACCESS_TOKEN_TIMEOUT.name());
        }
    }

    public List<UserResponse.DTO> 회원목록보기() {
        List<User> usersPS = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return usersPS.stream().map(UserResponse.DTO::new).toList(); // Java16
    }

    public UserResponse.DetailDTO 회원정보보기(int id) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );
        return new UserResponse.DetailDTO(userPS);
    }

    @Transactional
    public void 패스워드수정(int id, UserRequest.PasswordUpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );

        String encPassword = PasswordUtil.encode(requestDTO.getPassword());
        userPS.updatePassword(encPassword);
    }

    @Transactional
    public UserResponse.DTO 프로필사진수정(int id, UserRequest.ImgBase64UpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("id가 존재하지 않습니다 : "+id)
        );

        String imgUrl = MyFileUtil.write(requestDTO.getImgBase64());
        userPS.updateImgUrl(imgUrl);
        return new UserResponse.DTO(userPS);
    }
}
