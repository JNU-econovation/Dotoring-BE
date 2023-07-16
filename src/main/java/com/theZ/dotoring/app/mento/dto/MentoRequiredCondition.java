package com.theZ.dotoring.app.mento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MentoRequiredCondition {

    private List<String> majors;

    private List<String> jobs;

    public void initCondition(){
        if(this.jobs == null){
            this.jobs = new ArrayList<>();
        }
        if(this.majors == null){
            this.majors = new ArrayList<>();
        }
    }
}
