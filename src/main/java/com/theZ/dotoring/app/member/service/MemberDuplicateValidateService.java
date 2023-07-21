package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberNicknameRequestDTO;
import com.theZ.dotoring.app.member.repository.MemberRepository;
import com.theZ.dotoring.common.MessageCode;
import com.theZ.dotoring.exception.LoginIdDuplicateException;
import com.theZ.dotoring.exception.NicknameDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberDuplicateValidateService {

    private final MemberRepository memberRepository;

    public void validateNickname(MemberNicknameRequestDTO memberNicknameRequestDTO) {
        memberRepository.findAll().stream().forEach(i ->{
            if(i.getEmail().equals(memberNicknameRequestDTO.getNickname())){
                throw new NicknameDuplicateException(MessageCode.DUPLICATED_NICKNAME);
            }
        });
    }

    public void validateLoginId(String loginId) {
        // todo 리팩토링 시 이메일을 로그인 아이디로 바꾸기!
        memberRepository.findAll().stream().forEach(i -> {
            if(i.getLoginId().equals(loginId)){
                throw new LoginIdDuplicateException(MessageCode.DUPLICATED_LOGIN_ID);
            }
        });

    }

    public void verifyLoginId(String loginId) {
        // todo 리팩토링 시 이메일을 로그인 아이디로 바꾸기!
        boolean loginIdExists = memberRepository.findAll().stream()
                .anyMatch(member -> member.getLoginId().equals(loginId));

        if(!loginIdExists){
            throw new NoSuchElementException("해당 아이디가 존재하지 않습니다.");
        }

    }
}
