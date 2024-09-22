package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    // 게시글 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1씩 자동 증가
    @Column(name="board_no")
    private int no;

    // 제목
    @Column(name = "title", length = 20, nullable = false) // not null 길이 20
    private String title;

    // 내용
    @Column(name = "content", nullable = false)
    private String content;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;

    // 작성일
    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;

    // 조회수
    @Column(name = "count", nullable = false)
    @ColumnDefault("0")
    private int count;

    // 카테고리 번호
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "category_no")
    private Category category;

    @Builder
    public Board(String title, String content, User user, Category category) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
    }

    public void update(String title, String content, Category category){
        this.title = title;
        this.content = content;
        this.category = category;
    }

    @PrePersist
    public void preRegDate(){
        this.regDate = LocalDateTime.now();
    }

}
