package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.member.dto.EmailCodeRequestDTO;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.common.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMentoLoginIdHandler {

    private final MentoService mentoService;
    private final MemberEmailService memberEmailService;
    private final RedisUtil redisUtil;

    public String execute(EmailCodeRequestDTO emailCodeRequestDTO){
        String email = memberEmailService.validateCode(emailCodeRequestDTO.getCode(),emailCodeRequestDTO.getEmail());
        redisUtil.deleteData(emailCodeRequestDTO.getCode());
        return mentoService.findMentoLoginId(email);
    }
}
