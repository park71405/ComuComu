package com.comucomu.comu.DTO.Board;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardAddRequest {

    private String title;
    private String content;
    private String userId;
    private int category;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .user(User.builder()
                        .id(userId)
                        .build())
                .build();
    }

}
