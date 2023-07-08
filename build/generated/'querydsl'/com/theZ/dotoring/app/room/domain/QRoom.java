package com.theZ.dotoring.app.room.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoom is a Querydsl query type for Room
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoom extends EntityPathBase<Room> {

    private static final long serialVersionUID = -2085241505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoom room = new QRoom("room");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final ListPath<com.theZ.dotoring.app.letter.domain.Letter, com.theZ.dotoring.app.letter.domain.QLetter> letterList = this.<com.theZ.dotoring.app.letter.domain.Letter, com.theZ.dotoring.app.letter.domain.QLetter>createList("letterList", com.theZ.dotoring.app.letter.domain.Letter.class, com.theZ.dotoring.app.letter.domain.QLetter.class, PathInits.DIRECT2);

    public final com.theZ.dotoring.app.member.model.QMember receiver;

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.theZ.dotoring.app.member.model.QMember writer;

    public QRoom(String variable) {
        this(Room.class, forVariable(variable), INITS);
    }

    public QRoom(Path<? extends Room> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoom(PathMetadata metadata, PathInits inits) {
        this(Room.class, metadata, inits);
    }

    public QRoom(Class<? extends Room> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new com.theZ.dotoring.app.member.model.QMember(forProperty("receiver")) : null;
        this.writer = inits.isInitialized("writer") ? new com.theZ.dotoring.app.member.model.QMember(forProperty("writer")) : null;
    }

}

