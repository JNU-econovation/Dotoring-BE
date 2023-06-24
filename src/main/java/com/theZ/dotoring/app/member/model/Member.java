package com.theZ.dotoring.app.member.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.common.CommonEntity;
import com.theZ.dotoring.enums.Major;
import com.theZ.dotoring.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
public abstract class Member extends CommonEntity {

    @Column(name = "LOGIN_ID", unique = true)
    @Size(min = 8, max = 12)
    private String loginId;

    @Size(min = 7, max = 12)
    private String password;

    private String email;

    @Column(unique = true)
    @Size(min = 3, max = 8)
    private String nickname;

    @Size(min = 10, max = 100)
    private String introduction;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Major major;

    private String job;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Certification> certifications;

    public void mappingCertification(Certification... certifications){
        for(Certification certification : certifications){
            certification.mappingMember(this);
            this.certifications.add(certification);
        }
    }


}
