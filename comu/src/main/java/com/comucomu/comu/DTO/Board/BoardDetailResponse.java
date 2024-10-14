package com.comucomu.comu.DTO.Board;

import com.comucomu.comu.DTO.File.FileResponse;
import com.comucomu.comu.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponse {

    private int no;
    private String title;
    private String content;
    private String username;
    private LocalDateTime regDate;
    private FileResponse fileResponse = null;

    public BoardDetailResponse(Board board){
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUser().getNickname();
        this.regDate = board.getRegDate();
    }

    public void setFileResponse(FileResponse fileResponse) {
        this.fileResponse = fileResponse;
    }
}
