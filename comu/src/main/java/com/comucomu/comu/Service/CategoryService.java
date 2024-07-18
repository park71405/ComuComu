package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.Category.AddCategoryRequest;
import com.comucomu.comu.DTO.Category.UpdateCategoryRequest;
import com.comucomu.comu.Repository.CategoryRepository;
import com.comucomu.comu.entity.Category;
import jakarta.transaction.Transactional;
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

    // 카테고리
    @Transactional
    public void updateCategory(UpdateCategoryRequest request){

        // DB에서 id값 기준 데이터 찾기 (영속화)
        Category category  = categoryRepository.findById(request.getNo()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 id 부재 id no : " + request.getNo());
        });

        // 해당 값이 존재하는 경우 update 진행
        category.setCategoryName(request.getCategoryName());
        category.setPath(request.getPath());
        category.setIcon(request.getIcon());
        category.setComponent(request.getComponent());

        categoryRepository.save(category);


    }

}
