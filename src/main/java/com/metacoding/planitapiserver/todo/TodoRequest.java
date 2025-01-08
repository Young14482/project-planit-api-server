package com.metacoding.planitapiserver.todo;

import lombok.Data;

public class TodoRequest {



    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
