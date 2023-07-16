package com.theZ.dotoring.app.mento.model;

import com.theZ.dotoring.enums.Job;
import com.theZ.dotoring.enums.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentoFilterCondition {

    private List<Major> majors;

    private List<Job> jobs;

    public static MentoFilterCondition of(List<Major> majors, List<Job> jobs){
        return MentoFilterCondition.builder()
                .majors(majors)
                .jobs(jobs)
                .build();
    }
}

