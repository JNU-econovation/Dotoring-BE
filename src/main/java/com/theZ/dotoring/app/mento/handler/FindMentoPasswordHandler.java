package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.member.dto.MemberPasswordRequestDTO;
import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import com.theZ.dotoring.app.mento.dto.MentoAccountUpdateReq;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class FindMentoPasswordHandler {

    private final MemberDuplicateValidateService memberDuplicateValidateService;
    private final MemberEmailService memberEmailService;
    private final MentoService mentoService;
    public String execute(MemberPasswordRequestDTO memberPasswordRequestDTO) throws MessagingException {
        memberDuplicateValidateService.verifyLoginId(memberPasswordRequestDTO.getLoginId());
        String password = makePassword();
        memberEmailService.sendPasswordByEmail(memberPasswordRequestDTO.getEmail(),password);
        mentoService.updateMentoAccount(new MentoAccountUpdateReq(password),mentoService.findMentoByEmail(memberPasswordRequestDTO.getEmail()));
        return password;
    }

    private String makePassword(){
        return "dotoring1234";
    }


}
