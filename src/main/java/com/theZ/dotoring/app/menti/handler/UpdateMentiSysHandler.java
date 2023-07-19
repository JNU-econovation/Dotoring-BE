package com.theZ.dotoring.app.menti.handler;

import com.theZ.dotoring.app.menti.dto.MentiSysUpdateReqDTO;
import com.theZ.dotoring.app.menti.service.MentiService;
import com.theZ.dotoring.app.mento.dto.MentoSysUpdateReqDTO;
import com.theZ.dotoring.app.mento.service.MentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateMentiSysHandler {

    private final MentiService mentiService;

    // 부모 트랜잭션에서 findMento 해서 영속 엔티티를 자식 트랜잭션에게 인자로 넘겨줬다면 여전히 영속성에 있을까? -> 더티 체킹으로 테스팅해보기
    @Transactional
    public void execute(MentiSysUpdateReqDTO mentiSysUpdateReqDTO, Long Id) throws IOException {
        mentiService.updateMentoSys(mentiSysUpdateReqDTO, mentiService.findMenti(Id));
    }
}
