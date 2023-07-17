package com.theZ.dotoring.app.mento.handler;

import com.theZ.dotoring.app.member.mapper.MemberMapper;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoCardResponseDTO;
import com.theZ.dotoring.app.mento.dto.MentoRequiredCondition;
import com.theZ.dotoring.app.mento.service.MentoService;
import com.theZ.dotoring.app.mento.model.MentoFilterCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@RequiredArgsConstructor
public class FindAllMentoHandler {

    private final MentiService mentiService;
    private final MentoService mentoService;

    public Slice<MentoCardResponseDTO> execute(Long lastMentoId, Integer size, Long mentiId, MentoRequiredCondition mentoRequiredCondition){
        MentoFilterCondition mentoFilterCondition = makeFilterCondition(mentoRequiredCondition, mentiId);
        return mentoService.findAllMentoBySlice(lastMentoId, size, mentoFilterCondition);
    }

    @Transactional(readOnly = true)
    public MentoFilterCondition makeFilterCondition(MentoRequiredCondition mentoRequiredCondition, Long mentiId){
        if (mentoRequiredCondition.getJobs().isEmpty() & mentoRequiredCondition.getMajors().isEmpty()) {
            Menti menti = mentiService.findMenti(mentiId);
            MentoFilterCondition mentoFilterCondition = MentoFilterCondition.of(List.of(menti.getMajor()), List.of(menti.getJob()));
            return mentoFilterCondition;
        }
        MentoFilterCondition mentoFilterCondition = MentoFilterCondition.of(MemberMapper.ofMajor(mentoRequiredCondition.getMajors()), MemberMapper.ofJob(mentoRequiredCondition.getJobs()));
        return mentoFilterCondition;
    }

}
