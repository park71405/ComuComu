package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.Category;
import com.comucomu.comu.entity.QCategory;
import com.comucomu.comu.entity.QRoleToCategory;
import com.comucomu.comu.entity.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 카테고리 권한 별 조회
    public List<Category> findCategorysByRoleId(int roleId){

        QCategory category = QCategory.category;
        QRoleToCategory roleToCategory = QRoleToCategory.roleToCategory;

        return queryFactory
                .selectFrom(category)
                .leftJoin(category.roleToCategories, roleToCategory)
                .where(roleToCategory.role.id.eq(roleId))
                .fetch();
    }

}
