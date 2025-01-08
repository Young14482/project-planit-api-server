package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver._core.auth.SessionUser;
import com.metacoding.planitapiserver._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService postService;

    // 초기화면(오늘 할일목록) 가져오기
    @GetMapping("/init/post")
    public ResponseEntity<?> initPost(@RequestParam(defaultValue = "0") Integer page) {
        LocalDate date = LocalDate.now();
        return ResponseEntity.ok(ApiUtil.success(postService.게시글목록보기(page, date)));
    }
    //  날짜마다 할일 목록 가져오기
//    @GetMapping("/api/todo")
//    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "") String date) {
//        System.out.println("페이징 요청옴 : "+page);
//        return ResponseEntity.ok(ApiUtil.success(postService.게시글목록보기(page)));
//    }

    @PostMapping("/api/todo")
    public ResponseEntity<?> save(@RequestBody TodoRequest.SaveDTO requestDTO, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글쓰기(requestDTO, sessionUser)));
    }

    @GetMapping("/api/todo/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글상세보기(id, sessionUser.getId())));
    }

    @PutMapping("/api/todo/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TodoRequest.UpdateDTO requestDTO, @SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(postService.게시글수정하기(id, requestDTO, sessionUser.getId())));
    }

    @DeleteMapping("/api/todo/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @SessionUser User sessionUser) {
        postService.게시글삭제하기(id, sessionUser.getId());
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
