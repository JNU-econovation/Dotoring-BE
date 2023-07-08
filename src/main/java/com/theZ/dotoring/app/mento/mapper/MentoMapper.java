package com.theZ.dotoring.app.mento.mapper;

import com.theZ.dotoring.common.FileUtils;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.model.Mento;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MentoMapper {

    // entity -> dto : from
    public static List<MentoCardResponseDTO> from(List<Mento> mentoList){

        if(mentoList == null && mentoList.size() >0){
            return null;
        }

        List<MentoCardResponseDTO> mentoCardResponseDTOList = IntStream.range(0, mentoList.size())
                .mapToObj(i -> MentoCardResponseDTO.builder()
                        .mentoId(mentoList.get(i).getId())
                        .profileImageUrl(FileUtils.getFilePath(mentoList.get(i).getProfileImage()))
                        .nickname(mentoList.get(i).getNickname())
                        .job(mentoList.get(i).getJob().toString())
                        .major(mentoList.get(i).getMajor().toString())
                        .introduction(mentoList.get(i).getIntroduction())
                        .build()
                ).collect(Collectors.toList());

        return mentoCardResponseDTOList;
    }


}
