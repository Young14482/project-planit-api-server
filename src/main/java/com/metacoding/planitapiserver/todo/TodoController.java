package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @GetMapping("/api/todo/{todoId}")
    public ResponseEntity<?> findTodoDetail(@PathVariable Integer todoId) {
        todoService.findTodoDetail(todoId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @GetMapping("/api/todo/all")
    public ResponseEntity<?> findTodoWithCategory(@RequestBody Integer userId) {
        todoService.findTodoWithCategory(userId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @GetMapping("/api/todo")
    public ResponseEntity<?> findTodoOnly(@RequestBody Integer userId) {
        todoService.findTodoOnly(userId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @PostMapping("/api/todo")
    public ResponseEntity<?> findTodoDetail(@RequestBody Integer userId) {
        todoService.saveTodo(userId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @PutMapping("/api/todo/{todoId}")
    public ResponseEntity<?>updateTodo(@PathVariable Integer todoId, @RequestBody updatedto dto) {
        todoService.updateTodo(todoId, dto);
        return ResponseEntity.ok(ApiUtil.success(null));
    }

    @DeleteMapping("/api/todo/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
