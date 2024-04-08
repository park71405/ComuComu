package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBoardReqeust {

    private String title;
    private String content;
    private Category categoryId;

}
