package com.miraclerun.petopia.controller;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.dto.MemberDto;
import com.miraclerun.petopia.request.CreateMemberRequest;
import com.miraclerun.petopia.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/member")
    public Long join(@RequestBody CreateMemberRequest request) {
        return memberService.createMember(request);
    }

    /**
     * 회원 단건 조회
     */
    @GetMapping("/member/{memberId}")
    public MemberDto member(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);

        return new MemberDto(member);
    }

    /**
     * 회원 전체 조회
     */
    @GetMapping("/member")
    public List<MemberDto> members() {
        List<Member> members = memberService.getMembers();
        List<MemberDto> collect = members.stream()
                .map(MemberDto::new)
                .toList();

        return collect;
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/member/{memberId}")
    public void delete(@PathVariable Long memberId) {

        memberService.deleteMember(memberId);
    }
}
