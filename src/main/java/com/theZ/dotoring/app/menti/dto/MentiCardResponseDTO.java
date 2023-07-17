package com.theZ.dotoring.app.menti.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MentiCardResponseDTO {

    private Long id;
    private String profileImage;
    private String nickname;
    private Job job;
    private Major major;
    private String introduction;

    //@QueryProjection
    @Builder
    public MentiCardResponseDTO(Long id, String profileImage, String nickname, Job job, Major major, String introduction) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.job = job;
        this.major = major;
        this.introduction = introduction;
    }
}
