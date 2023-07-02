package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberLoginIdRequestDTO;
import com.theZ.dotoring.app.member.dto.MemberNicknameRequestDTO;
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

    public void validateNickname(MemberNicknameRequestDTO memberNicknameRequestDTO) {
        mentoRepository.findAll().stream().forEach(i ->{
            if(i.getEmail().equals(memberNicknameRequestDTO.getNickname())){
                throw new NicknameDuplicateException(MessageCode.DUPLICATED_NICKNAME);
            }
        });
    }

    public void validateLoginId(MemberLoginIdRequestDTO memberLoginIdRequestDTO) {
        mentoRepository.findAll().stream().forEach(i -> {
            if(i.getEmail().equals(memberLoginIdRequestDTO.getLoginId())){
                throw new LoginIdDuplicateException(MessageCode.DUPLICATED_LOGIN_ID);
            }
        });

    }

}
