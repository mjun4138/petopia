package com.miraclerun.petopia.auth;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.exception.MemberNotFound;
import com.miraclerun.petopia.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String grantType = "Bearer";
//    private static final Long accessTokenValidTime = 1000 * 5L; // 5초
    private static final Long accessTokenValidTime = 1000 * 60 * 30L; // 30분
//    private static final Long refreshTokenValidTime = 1000 * 5L; // 5초
    private static final Long refreshTokenValidTime = 1000 * 60 * 60 * 24 * 7L; // 30일
    private final Key key;
    private final MemberRepository memberRepository;


    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = Keys.hmacShaKeyFor(secretByteKey);
    }

    public JwtToken generateToken(Long memberId, Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        //Access Token
        String accessToken = Jwts.builder()
                .subject(memberId.toString())
                .claim("auth", authorities)
                .claim("email", member.getEmail())
                .claim("account", member.getAccount())
                .claim("name", member.getName())
                .claim("createdDate", member.getCreatedAt())
                .expiration(new Date(System.currentTimeMillis() + accessTokenValidTime))
                .signWith(key)
                .compact();
        //Refresh Token
        String refreshToken = Jwts.builder()
                .subject(memberId.toString())
                .claim("auth", authorities)
                .expiration(new Date(System.currentTimeMillis() + refreshTokenValidTime))
                .signWith(key)
                .compact();

        return JwtToken.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(accessToken).getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
