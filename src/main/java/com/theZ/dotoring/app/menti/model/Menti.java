package com.theZ.dotoring.app.menti.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.enums.Major;
import com.theZ.dotoring.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue("I")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menti extends Member {

    private String school;

    private Long grade;

    @Enumerated(EnumType.STRING)
    private Major major;

    @Size(min = 1, max = 300)
    private String preferredMentoring;

    @Builder
    public Menti(String loginId, String password, String email, String nickname, String introduction, String profileImage, Status status, List<Certification> certifications,String school,Long grade, Major major,String preferredMentoring) {
        super(loginId, password, email, nickname, introduction, profileImage, status, certifications);
        this.grade = grade;
        this.school = school;
        this.major = major;
        this.preferredMentoring = preferredMentoring;
    }

    public static Menti createMenti(String loginId, String password, String email, String nickname, String introduction, List<Certification> certifications,String school,Long grade, Major major){
        Menti menti = Menti.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .introduction(introduction)
                .status(Status.WAIT)
                .school(school)
                .grade(grade)
                .major(major)
                .build();
        menti.mappingCertification(certifications);
        return menti;
    }

}
