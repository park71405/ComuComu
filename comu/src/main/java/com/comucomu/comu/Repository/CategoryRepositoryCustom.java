package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Category;
import com.comucomu.comu.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 카테고리 권한 별 조회
    public List<Category> findCategorysByRoleId(int roleId){

        QCategory category = QCategory.category;

        return null;
    }

}
