package com.theZ.dotoring.app.menti.dto;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MentiInfoUpdateRequestDTO {

    @Size(min = 10, max = 100)
    private String introduction;

    private String profileImage;

    private String school;

    private Long grade;

    private Job job;

    private Major major;

    @Builder
    public MentiInfoUpdateRequestDTO(String introduction, String school, String profileImage, Long grade, Job job, Major major){
        this.introduction = introduction;
        this.school = school;
        this.profileImage = profileImage;
        this.grade = grade;
        this.job = job;
        this.major = major;
    }
}
