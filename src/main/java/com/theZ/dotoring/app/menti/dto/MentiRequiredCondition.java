package com.theZ.dotoring.app.menti.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MentiRequiredCondition {

    private List<String> majors;

    private List<String> jobs;

    // 다른 방법은 없을까?
    public void initCondition(){
        if(this.jobs == null){
            this.jobs = new ArrayList<>();
        }
        if(this.majors == null){
            this.majors = new ArrayList<>();
        }
    }
}
