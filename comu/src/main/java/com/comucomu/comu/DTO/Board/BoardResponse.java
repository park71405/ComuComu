package com.comucomu.comu.DTO.Board;

import com.comucomu.comu.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private final int no;
    private final String title;
    private final String content;
    private final String username;
    private final LocalDateTime regDate;
    private final int count;
    private final int category;

    public BoardResponse(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUser().getNickname();
        this.regDate = board.getRegDate();
        this.count = board.getCount();
        this.category = board.getCategory().getNo();
    }
}
