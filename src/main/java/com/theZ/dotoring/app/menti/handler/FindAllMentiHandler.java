package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.common.DefaultCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FindAllMentiHandler {

    private final MentiService mentiService;
    private final MentoService mentoService;

    @Transactional(readOnly = true)
    public Slice<MentiCardResponseDTO> execute(Long lastMentiId, Integer size, Long mentoId){
        Mento mento = mentoService.findMento(mentoId);
        DefaultCondition defaultCondition = DefaultCondition.of(mento.getMajor(), mento.getJob());
        return mentiService.findAllMentiBySlice(lastMentiId,size,defaultCondition);
    }
}
