package com.metacoding.planitapiserver.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserResponse {

    /* record >> java 16부터 정식 기능으로 출시된 새로운 클래스 타입
       final이 자동으로 선언되고 lombok의 @AllArgsConstructor와 @Data 처럼 생성자와 여러 기능이 자동으로 추가됨
     */
    public record DTO(Integer id, String username) {
        public DTO(User user) {
            this(user.getId(), user.getUsername());
        }
    }

    // jwt는 service -> controller 넘어갈 때만 사용
    record LoginDTO(@JsonIgnore String accessToken, Integer id, String username) {
        LoginDTO(String accessToken, User user) {
            this(accessToken, user.getId(), user.getUsername());
        }
    }

    record AutoLoginDTO(Integer id, String username) {
        AutoLoginDTO(User user) {
            this(user.getId(), user.getUsername());
        }
    }

    record FindIdDTO(Integer id, String username) {
        FindIdDTO(User user) {
            this(user.getId(), user.getUsername());
        }
    }
}