package com.miraclerun.petopia.controller;


import com.miraclerun.petopia.auth.JwtToken;
import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.request.LoginRequest;
import com.miraclerun.petopia.service.MemberService;
import com.miraclerun.petopia.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
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
    public JwtToken login(@RequestBody LoginRequest request, HttpServletResponse response) {

        JwtToken token = memberService.login(request.getAccount(), request.getPassword());
        Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        cookie.setSecure(false); //개발환경에서는 false
        cookie.setHttpOnly(true);

//        response.setHeader("Set-Cookie", cookie.getValue());
        response.addCookie(cookie);
        return token;
    }

    /**
     * 로그아웃
     */
    @PostMapping("/auth/members/{memberId}")
    public void logout(@PathVariable(name = "memberId") Long memberId) {
        Member member = memberService.getMember(memberId);
        refreshTokenService.deleteTokenByMember(member);
    }
}
