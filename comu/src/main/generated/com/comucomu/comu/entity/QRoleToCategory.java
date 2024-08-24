package com.comucomu.comu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleToCategory is a Querydsl query type for RoleToCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoleToCategory extends EntityPathBase<RoleToCategory> {

    private static final long serialVersionUID = -506773639L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleToCategory roleToCategory = new QRoleToCategory("roleToCategory");

    public final QCategory category;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QRole role;

    public QRoleToCategory(String variable) {
        this(RoleToCategory.class, forVariable(variable), INITS);
    }

    public QRoleToCategory(Path<? extends RoleToCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoleToCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoleToCategory(PathMetadata metadata, PathInits inits) {
        this(RoleToCategory.class, metadata, inits);
    }

    public QRoleToCategory(Class<? extends RoleToCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

