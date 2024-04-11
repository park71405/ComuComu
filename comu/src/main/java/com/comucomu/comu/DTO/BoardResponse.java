package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.Category;
import com.comucomu.comu.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private final int no;
    private final String title;
    private final String content;
    private final User user;
    private final LocalDateTime regDate;
    private final int count;
    private final Category category;

    public BoardResponse(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.user = board.getUser();
        this.regDate = board.getRegDate();
        this.count = board.getCount();
        this.category = board.getCategory();
    }
}
