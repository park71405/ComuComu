package com.comucomu.comu.DTO.Category;

import com.comucomu.comu.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private final int no;
    private final String categoryName;
    private final String path;
    private final String icon;
    private final String component;

    public CategoryResponse(Category category) {
        this.no = category.getNo();
        this.categoryName = category.getCategoryName();
        this.path = category.getPath();
        this.icon = category.getIcon();
        this.component = category.getComponent();
    }
}
