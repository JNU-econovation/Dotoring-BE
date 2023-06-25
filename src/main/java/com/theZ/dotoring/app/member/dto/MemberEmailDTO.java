package com.theZ.dotoring.app.member.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MemberEmailDTO {


    @Email(message = "이메일 패턴이 올바르지 않습니다.")
    private String email;

}
