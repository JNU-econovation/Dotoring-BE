package com.theZ.dotoring.app.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -186350906L;

    public static final QMember member = new QMember("member1");

    public final com.theZ.dotoring.app.commonModel.QCommonEntity _super = new com.theZ.dotoring.app.commonModel.QCommonEntity(this);

    public final ListPath<com.theZ.dotoring.app.certification.model.Certification, com.theZ.dotoring.app.certification.model.QCertification> certifications = this.<com.theZ.dotoring.app.certification.model.Certification, com.theZ.dotoring.app.certification.model.QCertification>createList("certifications", com.theZ.dotoring.app.certification.model.Certification.class, com.theZ.dotoring.app.certification.model.QCertification.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath dtype = createString("dtype");

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath introduction = createString("introduction");

    public final EnumPath<com.theZ.dotoring.enums.Job> job = createEnum("job", com.theZ.dotoring.enums.Job.class);

    public final StringPath loginId = createString("loginId");

    public final EnumPath<com.theZ.dotoring.enums.Major> major = createEnum("major", com.theZ.dotoring.enums.Major.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public final EnumPath<com.theZ.dotoring.enums.Status> status = createEnum("status", com.theZ.dotoring.enums.Status.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

