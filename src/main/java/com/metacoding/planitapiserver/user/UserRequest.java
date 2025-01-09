package com.metacoding.planitapiserver.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        User toEntity(String encPassword) {
            return User.builder()
                    .username(username)
                    .password(encPassword)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class PasswordUpdateDTO {
        private String password;
    }

    @Data
    public static class FindIdByEmailDTO {
        private String email;
    }

    @Data
    public static class FindPasswordByIdAndEmailDTO {
        private String email;
        private String username;
    }

}
