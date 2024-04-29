package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.BoardResponse;
import com.comucomu.comu.DTO.CategoryResponse;
import com.comucomu.comu.Service.CategoryService;
import com.comucomu.comu.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 전체조회
    @GetMapping("/cate/searchAll")
    public ResponseEntity<List<CategoryResponse>> findAllCategory(){
        List<CategoryResponse> categoryResponses = categoryService.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(categoryResponses);
    }

}
