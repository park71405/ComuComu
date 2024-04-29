package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private final int no;
    private final String categoryName;

    public CategoryResponse(Category category) {
        this.no = category.getNo();
        this.categoryName = category.getCategoryName();
    }
}
