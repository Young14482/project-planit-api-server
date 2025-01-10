package com.metacoding.planitapiserver.todo;

import lombok.Data;

import java.time.LocalDate;

public class TodoRequest {

    @Data
    public static class UpdateDTO {
        private String title;
        private Integer category;
        private String memo;
        private LocalDate dueDate;
        private String repeat;
        private Boolean isCompleted;
    }
}
