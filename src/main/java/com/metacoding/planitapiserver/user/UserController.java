package com.metacoding.planitapiserver.user;

import com.metacoding.planitapiserver._core.auth.SessionUser;
import com.metacoding.planitapiserver._core.error.exception.Exception403;
import com.metacoding.planitapiserver._core.util.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/auto/login")
    public ResponseEntity<?> autoLogin(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        UserResponse.AutoLoginDTO responseDTO = userService.자동로그인(accessToken);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        UserResponse.LoginDTO responseDTO = userService.로그인(requestDTO);
        return ResponseEntity.ok()
                .header("Authorization", responseDTO.accessToken())
                .body(ApiUtil.success(responseDTO));
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(userService.회원가입(requestDTO)));
    }


    @PutMapping("/api/user/{id}/password")
    public ResponseEntity<?> userPasswordUpdate(@PathVariable Integer id, @RequestBody UserRequest.PasswordUpdateDTO requestDTO, @SessionUser User sessionUser) {
        if (sessionUser.getId() != id) {
            throw new Exception403("해당 정보를 수정할 권한이 없습니다 : "+id);
        }
        userService.패스워드수정(id, requestDTO);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @PostMapping("/find-id")
    public ResponseEntity<?> findIDByEmailDTO(@RequestBody UserRequest.FindIdByEmailDTO findIdByEmailDTO) {
        UserResponse.FindIdDTO findIdDTO = userService.findIDByEmailDTO(findIdByEmailDTO);
        return ResponseEntity.ok(ApiUtil.success(findIdDTO));
    }

    @PutMapping("/find-password")
    public ResponseEntity<?> findPassword(@RequestBody UserRequest.FindPasswordByIdAndEmailDTO findPasswordByIdAndEmailDTO) {
        userService.findPassword(findPasswordByIdAndEmailDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("password", "1234");
        return ResponseEntity.ok(ApiUtil.success(response));
    }

    @PostMapping("/check-id")
    public ResponseEntity<?> checkId(@RequestBody UserRequest.CheckIdDTO requestDTO) {
        userService.checkId(requestDTO);
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
