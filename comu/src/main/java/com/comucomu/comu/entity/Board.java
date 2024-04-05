package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    // 게시글 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1씩 자동 증가
    private long no;

    // 제목
    @Column(name = "title", length = 20, nullable = false) // not null 길이 20
    private String title;

    // 내용
    @Column(name = "content", nullable = false)
    private String content;

    // 작성자

    // 작성일
    @CreatedDate
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;

    // 조회수
    @Column(name = "count", nullable = false)
    @ColumnDefault("0")
    private int count;

    // 카테고리 번호

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
