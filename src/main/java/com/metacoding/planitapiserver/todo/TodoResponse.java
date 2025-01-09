package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver.category.Category;
import com.metacoding.planitapiserver.category.CategoryResponse;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodoResponse {
    public record DTO(Integer id, String title, CategoryResponse.DTO category, String memo, String dueDate,
                      String createdAt, String repeat,
                      boolean isCompleted) {
        public DTO(Todo todo) {
            this(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getCategory() == null ? null : new CategoryResponse.DTO(todo.getCategory()),
                    todo.getMemo(),
                    todo.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                    todo.getRepeat(),
                    todo.getIsCompleted()
            );
        }
    }


    @Data
    public static class FindAllWithCategoryDTO {
        private List<CategoryResponse.DTO> categories;
        private List<DTO> todos;

        public FindAllWithCategoryDTO(List<Category> categories, List<Todo> todos) {
            this.categories = categories.stream().map(CategoryResponse.DTO::new).toList();
            this.todos = todos.stream().map(DTO::new).toList();
        }
    }

    @Data
    public static class FindAllDTO {
        private List<DTO> todos;

        public FindAllDTO(List<Todo> todos) {
            this.todos = todos.stream().map(DTO::new).toList();
        }
    }
}