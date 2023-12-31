package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.RefreshToken;
import com.miraclerun.petopia.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 토큰 저장
     */
    @Transactional
    public RefreshToken save(RefreshToken refreshToken) {

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * 토큰 업데이트
     */
    @Transactional
    public void update(RefreshToken refreshToken, String newToken) {
        refreshToken.updateToken(newToken);
    }

    /**
     * 토큰 삭제
     */
    @Transactional
    public void deleteToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findRefreshTokenByRefreshToken(refreshToken)
                .orElseThrow(RuntimeException::new);
        refreshTokenRepository.delete(token);
    }

    @Transactional
    public void deleteTokenByMember(Member member) {
        refreshTokenRepository.deleteRefreshTokenByMember(member);
        refreshTokenRepository.flush();
    }
}
