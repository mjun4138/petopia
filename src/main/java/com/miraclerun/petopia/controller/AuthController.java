package com.miraclerun.petopia.controller;


import com.miraclerun.petopia.auth.JwtToken;
import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.request.LoginRequest;
import com.miraclerun.petopia.service.MemberService;
import com.miraclerun.petopia.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    /**
     * 로그인
     */
    @PostMapping("/auth")
    public JwtToken login(@RequestBody LoginRequest request) {

        return memberService.login(request.getAccount(), request.getPassword());
    }

    /**
     * 로그아웃
     */
    @PostMapping("/auth/{memberId}")
    public void logout(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);
        refreshTokenService.deleteTokenByMember(member);
    }
}
