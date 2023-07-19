package com.theZ.dotoring.app.menti.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MentiSysUpdateReqDTO {

    @Size(min = 1, max = 300)
    private String preferredMentoring;

}
