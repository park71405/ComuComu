package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateCategoryRequest {

    private int no;
    private String categoryName;
    private String path;
    private String icon;
    private String component;

    public Category toEntity(){
        return Category.builder()
                .no(no)
                .categoryName(categoryName)
                .path(path)
                .icon(icon)
                .component(component)
                .build();
    }

}
