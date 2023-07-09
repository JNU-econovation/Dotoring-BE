package com.theZ.dotoring.app.letter.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLetter is a Querydsl query type for Letter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLetter extends EntityPathBase<Letter> {

    private static final long serialVersionUID = 2093831285L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLetter letter = new QLetter("letter");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.theZ.dotoring.app.room.domain.QRoom room;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.theZ.dotoring.app.member.model.QMember writer;

    public QLetter(String variable) {
        this(Letter.class, forVariable(variable), INITS);
    }

    public QLetter(Path<? extends Letter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLetter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLetter(PathMetadata metadata, PathInits inits) {
        this(Letter.class, metadata, inits);
    }

    public QLetter(Class<? extends Letter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new com.theZ.dotoring.app.room.domain.QRoom(forProperty("room"), inits.get("room")) : null;
        this.writer = inits.isInitialized("writer") ? new com.theZ.dotoring.app.member.model.QMember(forProperty("writer")) : null;
    }

}

