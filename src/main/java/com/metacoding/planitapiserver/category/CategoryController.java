package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver._core.auth.SessionUser;
import com.metacoding.planitapiserver._core.util.ApiUtil;
import com.metacoding.planitapiserver.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    // TODO 골조만 만든상태 컨트롤러 구현 해야함

    @GetMapping("/api/category")
    public ResponseEntity<?> findAll(@SessionUser User sessionUser) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.find(sessionUser.getId())));
    }

    @PostMapping("/api/category")
    public ResponseEntity<?> save(@SessionUser User sessionUser, @RequestBody CategoryRequest.saveDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.save(sessionUser.getId(), requestDTO)));
    }

    @PutMapping("/api/category/{categoryId}")
    public ResponseEntity<?> update(@PathVariable Integer categoryId, @RequestBody CategoryRequest.updateDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.update(categoryId, requestDTO)));
    }

    @DeleteMapping("/api/category/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer categoryId, @SessionUser User sessionUser) {
        categoryService.delete(sessionUser.getId(), categoryId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
