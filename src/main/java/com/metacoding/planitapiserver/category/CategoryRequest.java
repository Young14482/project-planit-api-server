package com.metacoding.planitapiserver.category;

import lombok.Data;

public class CategoryRequest {
    @Data
    public static class saveDTO {
        private String name;

        public saveDTO(String name) {
            this.name = name;
        }
    }

    @Data
    public static class updateDTO {
        private String name;

        public updateDTO(String name) {
            this.name = name;
        }
    }
}
