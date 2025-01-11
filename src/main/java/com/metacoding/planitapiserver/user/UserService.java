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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 2. file 경로 가져와서 유저정보 + 사진경로 DB 저장
        User userPS = userRepository.save(requestDTO.toEntity(encPassword));
        return new UserResponse.DTO(userPS);

    }

    // 엑세스토큰, 리플래시토큰을 돌려줘야 한다.
    public UserResponse.LoginDTO 로그인(UserRequest.LoginDTO requestDTO) {
        // 1. 유저 인증
        User userPS = userRepository.findByUsername(requestDTO.getUsername()).orElseThrow(
                ()-> new Exception401("유저네임을 찾을 수 없습니다")
        );
        if(!PasswordUtil.verify(requestDTO.getPassword(), userPS.getPassword())) throw new Exception401("패스워드가 일치하지 않습니다");

        // 2. jwt 생성
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

    @Transactional
    public void 패스워드수정(int id, UserRequest.PasswordUpdateDTO requestDTO) {
        User userPS = userRepository.findById(id).orElseThrow(
                ()-> new Exception404("올바르지 않은 아이디 입니다.")
        );
        System.out.println(requestDTO.getPassword());

        if(!PasswordUtil.verify(requestDTO.getPrev(), userPS.getPassword())) throw new Exception401("패스워드가 일치하지 않습니다");
        String encPassword = PasswordUtil.encode(requestDTO.getPassword());
        userPS.updatePassword(encPassword);
    }

    public UserResponse.FindIdDTO findIDByEmailDTO(UserRequest.FindIdByEmailDTO findIdByEmailDTO) {
        User userPs = userRepository.findByEmail(findIdByEmailDTO.getEmail()).orElseThrow(() -> new Exception404("해당 이메일로 가입한 아이디가 존재하지 않습니다."));
        return new UserResponse.FindIdDTO(userPs);
    }

    @Transactional
    public void findPassword(UserRequest.FindPasswordByIdAndEmailDTO findPasswordByIdAndEmailDTO) {
        User userPS = userRepository.findByUsernameAndEmail(findPasswordByIdAndEmailDTO.getEmail(),findPasswordByIdAndEmailDTO.getUsername()).orElseThrow(
                ()-> new Exception404("아이디 또는 이메일이 올바르지 않습니다")
        );

        String encPassword = PasswordUtil.encode("1234");
        userPS.updatePassword(encPassword);
    }

    public void checkId(UserRequest.CheckIdDTO requestDTO) {
        userRepository.findByUsername(requestDTO.getUsername()).orElseThrow(() -> new Exception404("없는 ID입니다."));
    }
}
