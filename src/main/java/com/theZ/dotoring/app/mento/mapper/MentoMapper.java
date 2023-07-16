package com.theZ.dotoring.app.mento.mapper;

import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.model.Mento;

public class MentoMapper {

    public static MentoCardResponseDTO from(Mento mento){

        return MentoCardResponseDTO.builder()
                .id(mento.getId())
                .nickname(mento.getNickname())
                .introduction(mento.getIntroduction())
                .profileImage(mento.getProfileImage())
                .major(mento.getMajor())
                .job(mento.getJob())
                .build();
    }
}
