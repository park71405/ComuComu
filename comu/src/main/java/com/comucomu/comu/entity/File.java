package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name="file")
public class File {

    @Id
    @Column(name = "file_no" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "path")
    private String path;

    @Column(name = "board_no")
    private int boardNo;

    @Column(name = "size")
    private long size;

    @Builder
    public File(int no, String originalName, String path, int boardNo, long size){
        this.no = no;
        this.originalName = originalName;
        this.path = path;
        this.boardNo = boardNo;
        this.size = size;
    }

}
