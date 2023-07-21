package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.member.dto.MemberPasswordRequestDTO;
import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class FindMentiPasswordHandler {

    private final MemberDuplicateValidateService memberDuplicateValidateService;
    private final MemberEmailService memberEmailService;

    public String execute(MemberPasswordRequestDTO memberPasswordRequestDTO) throws MessagingException {
        memberDuplicateValidateService.verifyLoginId(memberPasswordRequestDTO.getLoginId());
        String password = makePassword();
        memberEmailService.sendPasswordByEmail(memberPasswordRequestDTO.getEmail(),password);
        return password;
    }

    private String makePassword(){
        return "dotoring1234";
    }

}
