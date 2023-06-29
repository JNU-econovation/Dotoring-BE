package com.theZ.dotoring.app.mento.model;

import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("O")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mento extends Member {

    private String company;

    private Long careerLevel;

    private String job;

    @Size(min = 1, max = 300)
    private String mentoringSystem;

    @Builder
    public Mento(String loginId, String password, String email, String nickname, String introduction, String profileImage, Status status, List<Certification> certifications,String company, Long careerLevel,String job, String mentoringSystem) {
        super(loginId, password, email, nickname, introduction, profileImage, status, certifications);
        this.company = company;
        this.careerLevel = careerLevel;
        this.job = job;
        this.mentoringSystem = mentoringSystem;
    }

    public static Mento createMento(String loginId, String password, String email, String nickname, String introduction,List<Certification> certifications,String company, Long careerLevel,String job){
        Mento mento = Mento.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .introduction(introduction)
                .certifications(certifications)
                .status(Status.WAIT)
                .company(company)
                .careerLevel(careerLevel)
                .job(job)
                .build();
        mento.mappingCertification(certifications);
        return mento;
    }

}
