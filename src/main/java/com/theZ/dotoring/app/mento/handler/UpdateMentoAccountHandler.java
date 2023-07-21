package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.mento.dto.MentoAccountUpdateReq;
import com.theZ.dotoring.app.mento.dto.MentoInfoUpdateRequestDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateMentoAccountHandler {

    private final MentoService mentoService;

    private final MemberDuplicateValidateService DuplicateValidateService;

    @Transactional
    public void execute(MentoAccountUpdateReq mentoAccountUpdateReq, Long Id) throws IOException {
        // 아이디 중복 검사
        DuplicateValidateService.validateLoginId(mentoAccountUpdateReq.getLoginId());

        mentoService.updateMentoAccount(mentoAccountUpdateReq, mentoService.findMento(Id));
    }
}
