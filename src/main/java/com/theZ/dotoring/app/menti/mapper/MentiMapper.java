package com.theZ.dotoring.app.menti.mapper;

import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.model.Menti;

public class MentiMapper {

    public static MentiCardResponseDTO from(Menti menti){

        return MentiCardResponseDTO.builder()
                .id(menti.getId())
                .nickname(menti.getNickname())
                .introduction(menti.getIntroduction())
                .profileImage(menti.getProfileImage())
                .major(menti.getMajor())
                .job(menti.getJob())
                .build();
    }

}
