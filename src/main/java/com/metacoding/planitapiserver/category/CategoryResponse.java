package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver.todo.Todo;

import java.time.format.DateTimeFormatter;

public class CategoryResponse {
    public record DTO(Integer id, String name) {
        public DTO(Category category) {
            this(category.getId(), category.getName());
        }
    }
}
