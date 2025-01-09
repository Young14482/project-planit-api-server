package com.metacoding.planitapiserver.todo;

import lombok.Data;

import java.time.LocalDate;

public class TodoRequest {

    @Data
    public static class UpdateDTO {
        private String title;
        private String memo;
        private Integer category;
        private LocalDate dueDate;
    }
}
