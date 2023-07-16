package com.theZ.dotoring.app.member.mapper;

import com.theZ.dotoring.common.FileUtils;
import com.theZ.dotoring.app.member.dto.MemberInfoResponseDTO;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemberMapper {

    public static List<MemberInfoResponseDTO> from(List<Member> memberList){

        if(memberList == null && memberList.size() >0){
            return null;
        }

        List<MemberInfoResponseDTO> memberInfoResponseDTOList = IntStream.range(0, memberList.size())
                .mapToObj(i -> MemberInfoResponseDTO.builder()
                        .memberId(memberList.get(i).getId())
                        .nickname(memberList.get(i).getNickname())
                        .job(memberList.get(i).getJob().toString())
                        .major(memberList.get(i).getMajor().toString())
                        .certificationsUrl(FileUtils.getFilePathList(memberList.get(i).getCertifications()))
                        .build()
                ).collect(Collectors.toList());

        return memberInfoResponseDTOList;
    }

    public static List<Job> ofJob(List<String> jobs){

        if(jobs.isEmpty()){
            return List.of();
        }

        return IntStream.range(0, jobs.size())
                .mapToObj(i -> Job.getJob(jobs.get(i)))
                .collect(Collectors.toList());
    }

    public static List<Major> ofMajor(List<String> majors){

        if(majors.isEmpty()){
            return List.of();
        }

        return IntStream.range(0, majors.size())
                .mapToObj(i -> Major.getMajor(majors.get(i)))
                .collect(Collectors.toList());
    }
}
