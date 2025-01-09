package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    // TODO 골조만 만든상태 컨트롤러 구현 해야함

    @GetMapping("/api/category")
    public ResponseEntity<?> findAll(@RequestParam("user-id") Integer userId) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.findCategory(userId)));
    }

    @PostMapping("/api/category")
    public ResponseEntity<?> save(@RequestParam("user-id") Integer userId, @RequestBody CategoryRequest.saveDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.save(userId, requestDTO)));
    }

    @PutMapping("/api/category/{categoryId}")
    public ResponseEntity<?> update(@PathVariable Integer categoryId, @RequestBody CategoryRequest.updateDTO requestDTO) {
        return ResponseEntity.ok(ApiUtil.success(categoryService.updateCategory(categoryId, requestDTO)));
    }

    @DeleteMapping("/api/category/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok(ApiUtil.success(null));
    }
}
