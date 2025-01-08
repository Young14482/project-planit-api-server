package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver.user.UserResponse;

import java.time.format.DateTimeFormatter;

public class TodoResponse {
    public record DTO(Integer id, String title, String content, String createdAt,
                      UserResponse.DTO user) {
        public DTO(ToDo toDo) {
            this(
                    toDo.getId(),
                    toDo.getTitle(),
                    toDo.getContent(),
                    toDo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    new UserResponse.DTO(toDo.getUser())
            );
        }
    }

    public record DetailDTO(Integer id, String title, String content, String createdAt,
                            UserResponse.DTO user) {
        public DetailDTO(ToDo toDo,  int sessionUserId) {
            this(
                    toDo.getId(),
                    toDo.getTitle(),
                    toDo.getContent(),
                    toDo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    new UserResponse.DTO(toDo.getUser())
            );
        }

    }
}