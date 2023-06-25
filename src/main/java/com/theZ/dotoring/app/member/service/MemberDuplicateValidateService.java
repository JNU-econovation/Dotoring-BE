package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberEmailCodeDTO;
import com.theZ.dotoring.app.member.dto.MemberEmailDTO;
import com.theZ.dotoring.app.member.dto.MemberLoginIdDTO;
import com.theZ.dotoring.app.member.dto.MemberNicknameDTO;
import com.theZ.dotoring.app.member.repository.MemberRepository;
import com.theZ.dotoring.app.mento.repository.MentoRepository;
import com.theZ.dotoring.common.MessageCode;
import com.theZ.dotoring.exception.LoginIdDuplicateException;
import com.theZ.dotoring.exception.NicknameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDuplicateValidateService {

    private final MentoRepository mentoRepository;

    public void validateNickname(MemberNicknameDTO memberNicknameDTO) {
        mentoRepository.findAll().stream().forEach(i ->{
            if(i.getEmail().equals(memberNicknameDTO.getNickname())){
                throw new NicknameDuplicateException(MessageCode.DUPLICATED_NICKNAME);
            }
        });
    }

    public void validateLoginId(MemberLoginIdDTO memberLoginIdDTO) {
        mentoRepository.findAll().stream().forEach(i -> {
            if(i.getEmail().equals(memberLoginIdDTO.getLoginId())){
                throw new LoginIdDuplicateException(MessageCode.DUPLICATED_LOGIN_ID);
            }
        });
    }

}
