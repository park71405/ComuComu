package com.comucomu.comu.Service;

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

}
