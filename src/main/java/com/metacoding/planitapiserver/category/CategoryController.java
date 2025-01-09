package com.metacoding.planitapiserver.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    // TODO 골조만 만든상태 컨트롤러 구현 해야함
//
//    @GetMapping("/api/category")
//    public ResponseEntity<?> findCategory(@RequestParam Integer userid) {
//        categoryService.findCategory(userid);
//        return ResponseEntity.ok(ApiUtil.success(null));
//    }
//
//    @PostMapping("/api/category")
//    public ResponseEntity<?> saveCategory(@RequestParam Integer userid) {
//        categoryService.saveCategory(userid);
//        return ResponseEntity.ok(ApiUtil.success(null));
//    }
//
//    @PutMapping("/api/category")
//    public ResponseEntity<?> updateCategory(@RequestParam Integer categoryId, @RequestBody String name) {
//        categoryService.updateCategory(categoryId);
//        return ResponseEntity.ok(ApiUtil.success(null));
//    }
//
//    @DeleteMapping("/api/category")
//    public ResponseEntity<?> deleteCategory(@RequestParam Integer categoryId) {
//        categoryService.deleteCategory(categoryId);
//        return ResponseEntity.ok(ApiUtil.success(null));
//    }
}
