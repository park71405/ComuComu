package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.Category;
import com.comucomu.comu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBoardRequest {

    private String title;
    private String content;
    private User user;
    private Category category;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .category(category)
                .build();
    }

}
