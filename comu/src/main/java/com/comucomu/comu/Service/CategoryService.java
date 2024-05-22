package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.AddCategoryRequest;
import com.comucomu.comu.DTO.UpdateCategoryRequest;
import com.comucomu.comu.Repository.CategoryRepository;
import com.comucomu.comu.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 전체 조회
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    // 카테고리 추가
    public int addCategory(AddCategoryRequest request) {
        return categoryRepository.save(request.toEntity()).getNo();
    }

    // 카테고리 삭제
    public void deleteCategory(int category_no){
        categoryRepository.deleteById(category_no);
    }

    // 카테고리 수정
    public void updateCategory(UpdateCategoryRequest request){

        categoryRepository.updateCategory(request.getCategoryName(), request.getPath(), request.getIcon(), request.getComponent(), request.getNo());

    }

}
