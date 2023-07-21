package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.member.dto.EmailCodeRequestDTO;
import com.theZ.dotoring.app.member.service.MemberEmailService;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.common.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindMentiLoginIdHandler {

    private final MentiService mentiService;
    private final MemberEmailService memberEmailService;
    private final RedisUtil redisUtil;

    public String execute(EmailCodeRequestDTO emailCodeRequestDTO){
        String email = memberEmailService.validateCode(emailCodeRequestDTO.getCode());
        redisUtil.deleteData(emailCodeRequestDTO.getCode());
        return mentiService.findMentiLoginId(email);
    }
}
