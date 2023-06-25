package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberEmailCodeDTO;
import com.theZ.dotoring.app.member.dto.MemberEmailDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberEmailValidateService {

    public MemberEmailCodeDTO validateEmail(MemberEmailDTO memberEmailDTO) {
        return new MemberEmailCodeDTO("12345678");
        // todo 이메일 인증 구현시 프로젝트 상에 로그인 아이디 비밀번호 노출 안 되게끔 AWS Secrets Manager 이용할 것
    }
}
