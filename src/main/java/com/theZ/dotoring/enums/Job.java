package com.theZ.dotoring.enums;


import java.util.Arrays;
import java.util.List;

public enum Job {
    건설,경영,사무,금융,보험,교육,법률,사회복지,경찰,소방_군인,농림어업직,미용,여행,숙박,음식,경비,보건,의료,기계,금속,재료,전자,정보통신,연구_공학기술,영업,판매,운송,예술,디자인,방송,스포츠;

    public static Job getJob(String job){
        Job findJob = Arrays.stream(Job.values()).filter(j -> j.toString().equals(job)).findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직무입니다."));
        return findJob;
    }

    public static List<Job> getJobs(){
        return Arrays.stream(Job.values()).toList();
    }

}
