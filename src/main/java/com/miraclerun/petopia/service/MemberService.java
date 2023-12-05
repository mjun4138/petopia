package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.request.CreateMemberRequest;
import com.miraclerun.petopia.request.GetMembersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

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
     * 회원 단건 조회
     */
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);
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
                .orElseThrow(RuntimeException::new);
        memberRepository.delete(member);
    }
}
