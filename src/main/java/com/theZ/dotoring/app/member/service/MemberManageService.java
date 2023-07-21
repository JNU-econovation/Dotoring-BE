package com.theZ.dotoring.app.member.service;

import com.theZ.dotoring.app.member.dto.MemberInfoResponseDTO;
import com.theZ.dotoring.app.member.mapper.MemberMapper;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.member.repository.MemberQueryRepository;
import com.theZ.dotoring.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberManageService {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    public Page<MemberInfoResponseDTO> findWaitMember(Pageable pageable, String condition){
        Page<Member> memberPage = memberQueryRepository.findAllByCondition(pageable, condition);
        List<MemberInfoResponseDTO> memberInfoResponseDTOList = MemberMapper.from(memberPage.getContent());
        return new PageImpl<>(memberInfoResponseDTOList,pageable,memberPage.getTotalElements());
    }

    public void approveMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        member.approveStatus();
    }

}
