package com.theZ.dotoring.app.mento.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import com.theZ.dotoring.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.List;

@DiscriminatorValue("O")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mento extends Member {

    private String company;

    private Long careerLevel;

    @Size(min = 1, max = 300)
    private String mentoringSystem;

    @Builder
    public Mento(String loginId, String password, String email, String nickname, String introduction, String profileImage, Status status, List<Certification> certifications, String company, Long careerLevel, Job job, Major major, String mentoringSystem) {
        super(loginId, password, email, nickname, introduction, profileImage,"O", status, job, major, certifications);
        this.company = company;
        this.careerLevel = careerLevel;
        this.mentoringSystem = mentoringSystem;
    }

    public static Mento createMento(String loginId, String password, String email, String nickname, String introduction,String profileImage,List<Certification> certifications,String company, Long careerLevel,Job job, Major major){
        Mento mento = Mento.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .introduction(introduction)
                .profileImage(profileImage)
                .certifications(certifications)
                .status(Status.WAIT)
                .company(company)
                .careerLevel(careerLevel)
                .job(job)
                .major(major)
                .build();
        mento.mappingCertification(certifications);
        return mento;
    }

    public void updateCompany(String company) {
        this.company = company;
    }

    public void updateCareerLevel(Long careerLevel) {
        this.careerLevel = careerLevel;
    }

    public void updateMentoringSystem(String mentoringSystem) {this.mentoringSystem = mentoringSystem;}

}
