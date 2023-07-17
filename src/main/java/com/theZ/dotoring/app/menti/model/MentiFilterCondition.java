package com.theZ.dotoring.app.menti.model;

import com.theZ.dotoring.app.mento.model.MentoFilterCondition;
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
public class MentiFilterCondition {

    private List<Major> majors;

    private List<Job> jobs;

    public static MentiFilterCondition of(List<Major> majors, List<Job> jobs){
        return MentiFilterCondition.builder()
                .majors(majors)
                .jobs(jobs)
                .build();
    }
}
