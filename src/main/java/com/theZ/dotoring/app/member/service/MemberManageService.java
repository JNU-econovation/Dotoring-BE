package com.theZ.dotoring.app.member.service;

import antlr.Token;
import com.theZ.dotoring.app.auth.MemberDetails;
import com.theZ.dotoring.app.auth.TokenResponseDTO;
import com.theZ.dotoring.app.member.dto.MemberInfoResponseDTO;
import com.theZ.dotoring.app.member.mapper.MemberMapper;
import com.theZ.dotoring.app.member.model.Member;
import com.theZ.dotoring.app.member.repository.MemberQueryRepository;
import com.theZ.dotoring.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class MemberManageService implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberRepository memberRepository;

    public Page<MemberInfoResponseDTO> findWaitMember(Pageable pageable, String condition){
        Page<Member> memberPage = memberQueryRepository.findAllByCondition(pageable, condition);
        List<MemberInfoResponseDTO> memberInfoResponseDTOList = MemberMapper.from(memberPage.getContent());
        return new PageImpl<>(memberInfoResponseDTOList,pageable,memberPage.getTotalElements());
    }

    public void approveMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        member.approveStatus();
    }
    
    public TokenResponseDTO makeRefreshToken(String refreshToken){
        boolean isValidRefreshToken = TokenGenerator.isValidRefreshToken(refreshToken);

        // RefreshToken로부터 Member 가져와야 함
        String loginId = TokenGenerator.getClaimsFormToken(refreshToken).getSubject();

        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        if (isValidRefreshToken) {
            String accessToken = TokenGenerator.generateAccessToken(member);
            TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(accessToken, refreshToken);
            return tokenResponseDTO;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
      return memberRepository.findByLoginId(loginId).map(m -> new MemberDetails(m, Collections.singleton(new SimpleGrantedAuthority(m.getRole().getType())))).orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

}
