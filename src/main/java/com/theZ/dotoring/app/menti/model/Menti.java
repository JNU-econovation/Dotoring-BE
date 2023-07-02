package com.theZ.dotoring.app.menti.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import com.theZ.dotoring.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("I")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menti extends Member {

    private String school;

    private Long grade;

    @Size(min = 1, max = 300)
    private String preferredMentoring;

    @Builder
    public Menti(String loginId, String password, String email, String nickname, String introduction, String profileImage, Status status, Job job, Major major, List<Certification> certifications, String school, Long grade, String preferredMentoring) {
        super(loginId, password, email, nickname, introduction, profileImage, status,job,major,certifications);
        this.grade = grade;
        this.school = school;
        this.preferredMentoring = preferredMentoring;
    }

    public static Menti createMenti(String loginId, String password, String email, String nickname, String introduction, String profileImage, List<Certification> certifications,String school,Long grade, Major major,Job job){
        Menti menti = Menti.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .introduction(introduction)
                .profileImage(profileImage)
                .status(Status.WAIT)
                .school(school)
                .grade(grade)
                .major(major)
                .job(job)
                .build();
        menti.mappingCertification(certifications);
        return menti;
    }

}
