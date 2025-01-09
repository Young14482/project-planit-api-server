package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/api/todo/all")
    public ResponseEntity<?> findAllWithCategory(@RequestParam("user-id") Integer userId) {
        TodoResponse.FindAllWithCategoryDTO responseDTO = todoService.findAllWithCategory(userId);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @GetMapping("/api/todo")
    public ResponseEntity<?> findAll(@RequestParam("user-id") Integer userId) {
        TodoResponse.FindAllDTO responseDTO = todoService.findAll(userId);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @GetMapping("/api/todo/{todoId}")
    public ResponseEntity<?> findByTodoId(@PathVariable Integer todoId) {
        TodoResponse.DTO responseDTO = todoService.findByTodoId(todoId);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @PostMapping("/api/todo")
    public ResponseEntity<?> save(@RequestParam("user-id") Integer userId) {
        TodoResponse.DTO responseDTO = todoService.save(userId);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @PutMapping("/api/todo/{todoId}")
    public ResponseEntity<?> update(@PathVariable Integer todoId, @RequestBody TodoRequest.UpdateDTO requestDTO) {
        TodoResponse.DTO responseDTO = todoService.update(todoId, requestDTO);
        return ResponseEntity.ok(ApiUtil.success(responseDTO));
    }

    @DeleteMapping("/api/todo/{todoId}")
    public ResponseEntity<?> delete(@PathVariable Integer todoId) {
        todoService.delete(todoId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
