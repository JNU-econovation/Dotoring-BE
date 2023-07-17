package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.member.mapper.MemberMapper;
import com.theZ.dotoring.app.menti.dto.MentiCardResponseDTO;
import com.theZ.dotoring.app.menti.dto.MentiRequiredCondition;
import com.theZ.dotoring.app.menti.model.Menti;
import com.theZ.dotoring.app.menti.model.MentiFilterCondition;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoRequiredCondition;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.model.MentoFilterCondition;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllMentiHandler {

    private final MentiService mentiService;
    private final MentoService mentoService;


    @Transactional(readOnly = true)
    public Slice<MentiCardResponseDTO> execute(Long lastMentiId, Integer size, Long mentoId, MentiRequiredCondition mentiRequiredCondition){
        MentiFilterCondition mentiFilterCondition = makeFilterCondition(mentiRequiredCondition, mentoId);
        return mentiService.findAllMentiBySlice(lastMentiId, size, mentiFilterCondition);
    }

    /**
     *
     * @param mentiRequiredCondition : 멘티의 조건(학과, 직무)들을 담은 DTO
     * @param mentoId : 추후 Security 도입 후 Principle로 대체
     * @return mentiRequiredCondition
     */

    @Transactional(readOnly = true)
    public MentiFilterCondition makeFilterCondition(MentiRequiredCondition mentiRequiredCondition, Long mentoId){

        if (mentiRequiredCondition.getJobs().isEmpty() & mentiRequiredCondition.getMajors().isEmpty()) {
            Mento mento = mentoService.findMento(mentoId);
            // 아래 코드가 잘 이해가 안감 -> mentiFilterCondition은 찾고자 하는 멘티의 조건들이 아닌가? -> 왜 본인의 Major와 Job을 넣는가.
            // 아 유저가 아무 것도 안 적었으면, 본인에게 맞는 조건으로 검색이 되는구나? 오오
            // 나중에는 추천 알고리즘을 점수 부여하는 알고리즘으로 만들어도 재밌을 것 같다.
            MentiFilterCondition mentiFilterCondition = MentiFilterCondition.of(List.of(mento.getMajor()), List.of(mento.getJob()));
            return mentiFilterCondition;
        }

        // 만약 유저가 검색한 조건이 있다면, 해당 조건으로 검색한다.
        MentiFilterCondition mentiFilterCondition = MentiFilterCondition.of(MemberMapper.ofMajor(mentiRequiredCondition.getMajors()), MemberMapper.ofJob(mentiRequiredCondition.getJobs()));
        return mentiFilterCondition;
    }
}
