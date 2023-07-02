package com.theZ.dotoring.common;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultCondition {

    private Major major;

    private Job job;

    public static DefaultCondition of(Major major, Job job){
        return DefaultCondition.builder()
                .major(major)
                .job(job)
                .build();
    }
}

