package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.common.DefaultCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class FindAllMentoHandler {

    private final MentiService mentiService;
    private final MentoService mentoService;

    public Slice<MentoCardResponseDTO> execute(Long lastMentoId, Integer size, Long mentiId){
        Menti menti = mentiService.findMenti(mentiId);
        DefaultCondition defaultCondition = DefaultCondition.of(menti.getMajor(), menti.getJob());
        return mentoService.findAllMentoBySlice(lastMentoId,size,defaultCondition);
    }


}
