package com.comucomu.comu.DTO.Board;


import com.comucomu.comu.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardFileAddRequest {

    private String originalName;
    private String path;
    private int boardNo;
    private long size;

    public BoardFileAddRequest(String originalName, String path, int boardNo, long size){
        this.originalName = originalName;
        this.path = path;
        this.boardNo = boardNo;
        this.size = size;
    }

    public File toEntity(){
        return File.builder()
                .originalName(originalName)
                .path(path)
                .boardNo(boardNo)
                .size(size)
                .build();
    }

}
