package com.comucomu.comu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = -702102586L;

    public static final QFile file = new QFile("file");

    public final NumberPath<Integer> boardNo = createNumber("boardNo", Integer.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath originalName = createString("originalName");

    public final StringPath path = createString("path");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata metadata) {
        super(File.class, metadata);
    }

}

