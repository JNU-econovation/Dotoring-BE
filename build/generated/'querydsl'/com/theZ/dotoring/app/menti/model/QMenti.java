package com.theZ.dotoring.app.menti.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenti is a Querydsl query type for Menti
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenti extends EntityPathBase<Menti> {

    private static final long serialVersionUID = 770130070L;

    public static final QMenti menti = new QMenti("menti");

    public final com.theZ.dotoring.app.member.model.QMember _super = new com.theZ.dotoring.app.member.model.QMember(this);

    //inherited
    public final ListPath<com.theZ.dotoring.app.certification.model.Certification, com.theZ.dotoring.app.certification.model.QCertification> certifications = _super.certifications;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath email = _super.email;

    public final NumberPath<Long> grade = createNumber("grade", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath introduction = _super.introduction;

    //inherited
    public final EnumPath<com.theZ.dotoring.enums.Job> job = _super.job;

    //inherited
    public final StringPath loginId = _super.loginId;

    //inherited
    public final EnumPath<com.theZ.dotoring.enums.Major> major = _super.major;

    //inherited
    public final StringPath nickname = _super.nickname;

    //inherited
    public final StringPath password = _super.password;

    public final StringPath preferredMentoring = createString("preferredMentoring");

    //inherited
    public final StringPath profileImage = _super.profileImage;

    public final StringPath school = createString("school");

    //inherited
    public final EnumPath<com.theZ.dotoring.enums.Status> status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMenti(String variable) {
        super(Menti.class, forVariable(variable));
    }

    public QMenti(Path<? extends Menti> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenti(PathMetadata metadata) {
        super(Menti.class, metadata);
    }

}

