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

    public TodoResponse.FindAllWithCategoryDTO findAllWithCategory(User sessionUser) {
        List<Category> categories = List.of(
                new Category(1, "작업", sessionUser),
                new Category(2, "생일", sessionUser),
                new Category(3, "공부", sessionUser)
        ); // TODO : category 완성 시 수정
        List<Todo> todos = todoRepository.findAllByUserId(sessionUser.getId());
        return new TodoResponse.FindAllWithCategoryDTO(categories, todos);
    }

    public TodoResponse.FindAllDTO findAll(User sessionUser) {
        List<Todo> todos = todoRepository.findAllByUserId(sessionUser.getId());
        return new TodoResponse.FindAllDTO(todos);
    }

    public TodoResponse.DTO findByTodoId(Integer todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new Exception404("투두를 찾을 수 없습니다."));
        return new TodoResponse.DTO(todo);
    }

    @Transactional
    public TodoResponse.DTO save( User sessionUser) {
        Todo todo = Todo.builder().user(sessionUser).build();
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
