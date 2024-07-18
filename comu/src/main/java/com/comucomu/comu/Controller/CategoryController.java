package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.Category.AddCategoryRequest;
import com.comucomu.comu.DTO.Category.CategoryResponse;
import com.comucomu.comu.DTO.Category.UpdateCategoryRequest;
import com.comucomu.comu.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 카테고리 추가
    @PostMapping("/cate")
    public int AddCategory(@RequestBody AddCategoryRequest addCategoryRequest) {

        int categoryNo = categoryService.addCategory(addCategoryRequest);

        return categoryNo;
    }

    // 카테고리 삭제
    @DeleteMapping("/cate")
    public void DeleteCategory(@RequestParam("no") String category_no){

        categoryService.deleteCategory(Integer.parseInt(category_no));
    }

    // 카테고리 수정
    @PutMapping("/cate")
    public void UpdateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest){
        categoryService.updateCategory(updateCategoryRequest);
    }

}
