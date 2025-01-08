package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver.user.UserResponse;

import java.time.format.DateTimeFormatter;

public class TodoResponse {
    public record DTO(Integer id, String title, String content, String createdAt,
                      UserResponse.DTO user) {
        public DTO(ToDo todo) {
            this(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getMemo(),
                    todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    new UserResponse.DTO(todo.getUser())
            );
        }
    }

    public record DetailDTO(Integer id, String title, String content, String createdAt,
                            UserResponse.DTO user) {
        public DetailDTO(ToDo todo,  int sessionUserId) {
            this(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getMemo(),
                    todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    new UserResponse.DTO(todo.getUser())
            );
        }

    }
}