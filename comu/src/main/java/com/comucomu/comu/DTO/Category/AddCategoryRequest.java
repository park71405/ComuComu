package com.comucomu.comu.DTO.Category;

import com.comucomu.comu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCategoryRequest {

    private String categoryName;
    private String path;
    private String icon;
    private String component;

    public Category toEntity(){
        return Category.builder()
                .categoryName(categoryName)
                .path(path)
                .icon(icon)
                .component(component)
                .build();
    }

}
