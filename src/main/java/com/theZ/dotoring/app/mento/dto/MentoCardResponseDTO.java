package com.theZ.dotoring.app.mento.dto;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MentoCardResponseDTO {

    private Long id;
    private String profileImage;
    private String nickname;
    private String mentoringSystem;
    private Job job;
    private Major major;
    private String introduction;

    @Builder
    public MentoCardResponseDTO(Long id, String profileImage, String nickname, String mentoringSystem, Job job, Major major, String introduction) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.mentoringSystem = mentoringSystem;
        this.job = job;
        this.major = major;
        this.introduction = introduction;
    }
}
