package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.menti.dto.MentiInfoUpdateRequestDTO;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoInfoUpdateRequestDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateMentiInfoHandler {

    private final MentiService mentiService;

    @Transactional
    public void execute(MentiInfoUpdateRequestDTO mentiInfoUpdateRequestDTO, Long Id) throws IOException {
        mentiService.updateMentiInfo(mentiInfoUpdateRequestDTO, mentiService.findMenti(Id));
    }
}
