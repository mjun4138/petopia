package com.miraclerun.petopia.repository;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>, RefreshTokenRepositoryCustom {
    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);

    void deleteRefreshTokenByMember(Member member);
}
