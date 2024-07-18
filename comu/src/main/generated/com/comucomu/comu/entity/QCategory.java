package com.comucomu.comu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = 1224037960L;

    public static final QCategory category = new QCategory("category");

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath categoryName = createString("categoryName");

    public final StringPath component = createString("component");

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath path = createString("path");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

