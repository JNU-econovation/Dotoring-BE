package com.theZ.dotoring.app.menti.model;

import com.theZ.dotoring.app.member.model.Member;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("I")
public class Menti extends Member {

    @Size(min = 1, max = 300)
    private String preferredMentoring;

    private Long grade;
}
