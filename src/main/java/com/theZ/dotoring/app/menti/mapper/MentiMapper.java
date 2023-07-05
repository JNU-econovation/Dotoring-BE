package com.theZ.dotoring.app.menti.mapper;

import com.theZ.dotoring.app.commonModel.FileUtils;
import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.model.Menti;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MentiMapper {

    public static List<MentiCardResponseDTO> from(List<Menti> mentiList){

        if(mentiList == null && mentiList.size() >0){
            return null;
        }

        List<MentiCardResponseDTO> mentiCardResponseDTOList = IntStream.range(0, mentiList.size())
                .mapToObj(i -> MentiCardResponseDTO.builder()
                        .mentoId(mentiList.get(i).getId())
                        .profileImage(FileUtils.getFullPath(mentiList.get(i).getProfileImage()))
                        .nickname(mentiList.get(i).getNickname())
                        .job(mentiList.get(i).getJob().toString())
                        .major(mentiList.get(i).getMajor().toString())
                        .introduction(mentiList.get(i).getIntroduction())
                        .build()
                ).collect(Collectors.toList());

        return mentiCardResponseDTOList;
    }
}
