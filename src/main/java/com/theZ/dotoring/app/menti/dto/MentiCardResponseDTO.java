package com.theZ.dotoring.app.menti.dto;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
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

}
