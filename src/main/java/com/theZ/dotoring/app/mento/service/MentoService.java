package com.theZ.dotoring.app.mento.service;
import com.theZ.dotoring.app.certification.model.Certification;
import com.theZ.dotoring.app.mento.dto.MentoSignupDTO;
import com.theZ.dotoring.app.mento.model.Mento;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoService {

    private final MentoRepository mentoRepository;
    @Transactional
    public void saveMento(MentoSignupDTO mentoSignupDTO, List<Certification> certifications){
        Mento mento = Mento.createMento(mentoSignupDTO.getLoginId(), mentoSignupDTO.getPassword(), mentoSignupDTO.getEmail(), mentoSignupDTO.getNickname(), mentoSignupDTO.getIntroduction(), certifications, mentoSignupDTO.getCompany(), mentoSignupDTO.getCareerLevel(), mentoSignupDTO.getJob());
        mentoRepository.save(mento);
    }
}
