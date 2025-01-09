package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver._core.error.exception.Exception404;
import com.metacoding.planitapiserver.category.Category;
import com.metacoding.planitapiserver.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponse.FindAllWithCategoryDTO findAllWithCategory(Integer userId) {
        List<Category> categories = List.of(
                new Category(1, "작업", User.builder().id(1).build()),
                new Category(2, "생일", User.builder().id(1).build()),
                new Category(3, "공부", User.builder().id(1).build())
        ); // TODO : category 완성 시 수정
        List<Todo> todos = todoRepository.findAllByUserId(userId);
        return new TodoResponse.FindAllWithCategoryDTO(categories, todos);
    }

    public TodoResponse.FindAllDTO findAll(Integer userId) {
        List<Todo> todos = todoRepository.findAllByUserId(userId);
        return new TodoResponse.FindAllDTO(todos);
    }

    public TodoResponse.DTO findByTodoId(Integer todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new Exception404("투두를 찾을 수 없습니다."));
        return new TodoResponse.DTO(todo);
    }

    @Transactional
    public TodoResponse.DTO save(Integer userId) {
        Todo todo = Todo.builder().user(User.builder().id(userId).build()).build();
        todoRepository.save(todo);
        return new TodoResponse.DTO(todo);
    }

    @Transactional
    public TodoResponse.DTO update(Integer todoId, TodoRequest.UpdateDTO requestDTO) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new Exception404("투두를 찾을 수 없습니다."));
        todo.update(requestDTO);
        return new TodoResponse.DTO(todo);
    }

    @Transactional
    public void delete(Integer todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new Exception404("투두를 찾을 수 없습니다."));
        todo.delete();
    }
}
