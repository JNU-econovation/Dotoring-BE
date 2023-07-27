package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberEmailCodeResponseDTO;
import com.theZ.dotoring.app.member.dto.MemberEmailRequestDTO;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.member.repository.MemberRepository;
import com.theZ.dotoring.common.MessageCode;
import com.theZ.dotoring.common.RedisUtil;
import com.theZ.dotoring.exception.EmailCodeException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class MemberEmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine engine;
    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;

    @Value("${email.validTime}")
    private Long validTime;

    @Transactional
    public MemberEmailCodeResponseDTO sendEmail(MemberEmailRequestDTO memberEmailRequestDTO) throws MessagingException {
        MemberEmailCodeResponseDTO memberEmailCodeResponseDTO = createCode();
        redisUtil.setDataAndExpire(memberEmailCodeResponseDTO.getEmailVerificationCode(),memberEmailRequestDTO.getEmail(),validTime);
//        MimeMessage message = javaMailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, memberEmailRequestDTO.getEmail()); // 보낼 이메일 설정
//        message.setSubject("안녕하세요. dotoring입니다. " + memberEmailCodeResponseDTO.getEmailVerificationCode()); // 이메일 제목
//        message.setText(setContext(memberEmailCodeResponseDTO.getEmailVerificationCode()), "utf-8", "html"); // 내용 설정(Template Process)
//        javaMailSender.send(message);
        // todo 실제 이메일이 만들어진다면 사용!
        return memberEmailCodeResponseDTO;
    }

    public void sendPasswordByEmail(String email, String password){
//        MimeMessage message = javaMailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
//        message.setSubject("안녕하세요. dotoring입니다."); // 이메일 제목
//        message.setText(setContext(password, "utf-8", "html"); // 내용 설정(Template Process)
//        javaMailSender.send(message);
    }

    private String setContextToCode(String code) { // 타임리프 설정하는 코드
        Context context = new Context();
        context.setVariable("code", code);
        return engine.process("mailCode", context);
    }

    private String setContextToPassword(String password){
        Context context = new Context();
        context.setVariable("password", password);
        return engine.process("mailPassword", context);
    }

    private MemberEmailCodeResponseDTO createCode() {
        return new MemberEmailCodeResponseDTO("12345678");
    }



    public String validateCode(String code, String email){
        String savedEmail = redisUtil.getData(code); // 입력 받은 인증 코드(key)를 이용해 email(value)을 꺼낸다.
        if (email == null || !savedEmail.equals(email)) { // email이 존재하지 않으면, 유효 기간 만료이거나 코드 잘못 입력
            throw new EmailCodeException(MessageCode.WRONG_CODE);
        }
        return email;
    }


}
