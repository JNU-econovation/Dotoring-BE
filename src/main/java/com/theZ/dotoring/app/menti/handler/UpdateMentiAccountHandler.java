package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.member.service.MemberDuplicateValidateService;
import com.theZ.dotoring.app.menti.dto.MentiAccountUpdateReq;
import com.theZ.dotoring.app.menti.service.MentiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateMentiAccountHandler {

    private final MentiService mentiService;

    private final MemberDuplicateValidateService DuplicateValidateService;

    @Transactional
    public void execute(MentiAccountUpdateReq mentiAccountUpdateReq, Long Id) throws IOException {

        // 아이디 중복 검사
        DuplicateValidateService.validateLoginId(mentiAccountUpdateReq.getLoginId());

        mentiService.updateMentiAccount(mentiAccountUpdateReq, mentiService.findMenti(Id));
    }
}
