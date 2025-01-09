package com.metacoding.planitapiserver.category;

import lombok.Data;

import java.util.List;

public class CategoryResponse {
    public record DTO(Integer id, String name) {
        public DTO(Category category) {
            this(category.getId(), category.getName());
        }
    }

    @Data
    public static class DTOList {
        private List<DTO> dtos;

        public DTOList(List<Category> categories) {
            this.dtos = categories.stream().map(DTO::new).toList();
        }
    }
}
