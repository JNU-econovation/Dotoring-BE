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
    private Job job;
    private Major major;
    private String introduction;

}
