package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver._core.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/category/{userid}")
    public ResponseEntity<?> autoLogin(@RequestParam Integer userid) {

        return ResponseEntity.ok(ApiUtil.success(null));
    }

}
