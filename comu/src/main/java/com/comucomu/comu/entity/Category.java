package com.comucomu.comu.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="category")
public class Category {

    // 카테고리 no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no")
    private int no;

    // 경로
    @Column(nullable = false, name="path")
    private String path;

    // 카테고리 이름
    @Column(nullable = false, name="category_name")
    private String categoryName;

    // icon
    @Column(nullable = false, name="icon")
    private String icon;

    // component
    @Column(nullable = false, name="component")
    private String component;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RoleToCategory> roleToCategories;

    @Builder
    public Category(int no, String path, String categoryName, String icon, String component, List<Board> boardList) {
        this.no = no;
        this.path = path;
        this.categoryName = categoryName;
        this.icon = icon;
        this.component = component;
        this.boardList = boardList;
    }
}
