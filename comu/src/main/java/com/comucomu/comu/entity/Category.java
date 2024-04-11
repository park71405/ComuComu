package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="category")
public class Category {

    // 카테고리 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no")
    private int no;

    // 카테고리 이름
    @Column(nullable = false, name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Board> boardList = new ArrayList<>();


}
