package com.theZ.dotoring.app.member.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.commonModel.CommonEntity;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import com.theZ.dotoring.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_job" ,columnList = "job"),
        @Index(name = "idx_major", columnList = "major")
})
public abstract class Member extends CommonEntity {

    @Column(name = "LOGIN_ID", unique = true)
    @Size(min = 8, max = 12)
    private String loginId;

    @Size(min = 7, max = 12)
    private String password;

    @Email
    private String email;

    @Column(unique = true)
    @Size(min = 3, max = 8)
    private String nickname;

    @Size(min = 10, max = 100)
    private String introduction;

    private String profileImage;

    @Column(name = "DTYPE", insertable = false, updatable = false)
    private String dtype;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Job job;

    @Enumerated(EnumType.STRING)
    private Major major;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Certification> certifications = new ArrayList<>();

    public void mappingCertification(List<Certification> certifications){
        for(Certification certification : certifications){
            certification.mappingMember(this);
        }
    }

    public void approveStatus(){
        this.status = Status.ACTIVE;
    }

    public void updateProfileImage(String profileImage) { this.profileImage = profileImage; }

    public void updateMajor(Major major) {
        this.major = major;
    }

    public void updateJob(Job job) { this.job = job;}

    public void updateIntroduction(String introduction) { this.introduction = introduction; }

    public void updateloginId(String loginId) { this.loginId = loginId; }

    public void updatePwd(String password) { this.password = password; }
}
