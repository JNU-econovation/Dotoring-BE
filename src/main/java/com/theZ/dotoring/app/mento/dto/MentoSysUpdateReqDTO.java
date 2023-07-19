package com.theZ.dotoring.app.mento.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MentoSysUpdateReqDTO {

    @Size(min = 1, max = 300)
    private String mentoringSystem;

}
