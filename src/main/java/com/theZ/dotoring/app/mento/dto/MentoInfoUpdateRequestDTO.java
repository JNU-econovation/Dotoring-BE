package com.theZ.dotoring.app.mento.dto;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MentoInfoUpdateRequestDTO {

    @Size(min = 10, max = 100)
    private String introduction;

    private String profileImage;

    private String company;

    private Long careerLevel;

    private Job job;

    private Major major;

    @Builder
    public MentoInfoUpdateRequestDTO(String introduction, String company, String profileImage, Long careerLevel, Job job, Major major){
        this.introduction = introduction;
        this.company = company;
        this.profileImage = profileImage;
        this.careerLevel = careerLevel;
        this.job = job;
        this.major = major;
    }
}
