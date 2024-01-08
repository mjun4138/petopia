package com.miraclerun.petopia.service;

import com.miraclerun.petopia.auth.JwtToken;
import com.miraclerun.petopia.auth.JwtTokenProvider;
import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.RefreshToken;
import com.miraclerun.petopia.exception.MemberNotFound;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.request.CreateMemberRequest;
import com.miraclerun.petopia.request.GetMembersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder encoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원 등록
     */
    @Transactional
    public Long createMember(CreateMemberRequest request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .account(request.getAccount())
                .password(encoder.encode(request.getPassword()))
                .build();
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 로그인
     */
    @Transactional
    public JwtToken login(String account, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Member member = memberRepository.findByAccount(account)
                .orElseThrow(MemberNotFound::new);

        JwtToken token = jwtTokenProvider.generateToken(member.getId(), authentication);
        refreshTokenService.deleteTokenByMember(member);
        RefreshToken refreshToken = RefreshToken.builder()
                .member(member)
                .refreshToken(token.getRefreshToken())
                .build();
        refreshTokenService.save(refreshToken);

        return token;
    }

    /**
     * 회원 단건 조회
     */
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> getMembers(GetMembersRequest request) {
        return memberRepository.getMembers(request);
    }

    /**
     * 회원 전체 페이지 수
     */
    public int getTotalPages(GetMembersRequest request) {
        Long totalMembers = memberRepository.totalMembers();
        int totalPages = (int) Math.ceil((double) totalMembers / request.getSize());

        return totalPages;
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFound::new);
        memberRepository.delete(member);
    }
}
