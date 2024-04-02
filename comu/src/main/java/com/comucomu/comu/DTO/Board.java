package com.comucomu.comu.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Entity
@Setter
public class Board {

    // 게시글 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    // 제목
    private String title;

    // 내용
    private String content;

    // 작성자 id
    private String userid;

    // 작성일
    private Date regDate;

    // 조회수
    private int count;

    // 카테고리 번호
    private int categoryNo;

}
