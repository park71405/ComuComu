package com.comucomu.comu.DTO;

import com.comucomu.comu.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardViewResponse {

    private Long no;
    private String title;
    private String content;
    private LocalDateTime regDate;

    public BoardViewResponse(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.regDate = board.getRegDate();
    }
}
