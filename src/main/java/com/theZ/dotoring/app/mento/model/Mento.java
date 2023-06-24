package com.theZ.dotoring.app.mento.model;

import com.theZ.dotoring.app.member.model.Member;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("O")
public class Mento extends Member {

    private String company;

    private Long careerLevel;

    @Size(min = 1, max = 300)
    private String mentoringSystem;
}
