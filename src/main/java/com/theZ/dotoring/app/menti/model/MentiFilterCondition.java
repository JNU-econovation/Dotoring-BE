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

    private Major major;

    private Job job;

    public static MentiFilterCondition of(Major major, Job job){
        return MentiFilterCondition.builder()
                .major(major)
                .job(job)
                .build();
    }
}
