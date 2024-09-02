package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.Category.AddCategoryRequest;
import com.comucomu.comu.DTO.Category.UpdateCategoryRequest;
import com.comucomu.comu.Repository.CategoryRepository;
import com.comucomu.comu.Repository.CategoryRepositoryCustom;
import com.comucomu.comu.Repository.RoleRepository;
import com.comucomu.comu.entity.Category;
import com.comucomu.comu.entity.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryCustom categoryRepositoryCustom;
    private final RoleRepository roleRepository;

    // 카테고리 전체 조회
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    // 카테고리 권한 별 조회
    public List<Category> findAll(String role){

        // role_id 찾기
        int roleId = roleRepository.findByRoleName(role)
                .map(Role::getId)
                .orElse(3);

        // roleId에 따라 카테고리 조회
        return categoryRepositoryCustom.findCategorysByRoleId(roleId);
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
