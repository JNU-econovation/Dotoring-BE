package com.theZ.dotoring.app.mento.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMento is a Querydsl query type for Mento
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMento extends EntityPathBase<Mento> {

    private static final long serialVersionUID = -990287966L;

    public static final QMento mento = new QMento("mento");

    public final com.theZ.dotoring.app.member.model.QMember _super = new com.theZ.dotoring.app.member.model.QMember(this);

    public final NumberPath<Long> careerLevel = createNumber("careerLevel", Long.class);

    //inherited
    public final ListPath<com.theZ.dotoring.app.certification.model.Certification, com.theZ.dotoring.app.certification.model.QCertification> certifications = _super.certifications;

    public final StringPath company = createString("company");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath email = _super.email;

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

    public final StringPath mentoringSystem = createString("mentoringSystem");

    //inherited
    public final StringPath nickname = _super.nickname;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final StringPath profileImage = _super.profileImage;

    //inherited
    public final EnumPath<com.theZ.dotoring.enums.Status> status = _super.status;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMento(String variable) {
        super(Mento.class, forVariable(variable));
    }

    public QMento(Path<? extends Mento> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMento(PathMetadata metadata) {
        super(Mento.class, metadata);
    }

}

