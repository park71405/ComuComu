package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Board;
import com.comucomu.comu.entity.User;
import lombok.Getter;

@Getter
public class BoardListViewResponse {

    private final Long no;
    private final String title;
    private final String content;

    public BoardListViewResponse(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
