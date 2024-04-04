package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private final long no;
    private final String title;
    private final String content;
    private final LocalDateTime regDate;
    private final int count;

    public BoardResponse(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.regDate = board.getRegDate();
        this.count = board.getCount();
    }
}
