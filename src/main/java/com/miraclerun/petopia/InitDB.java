package com.miraclerun.petopia;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final initService initService;

    @PostConstruct
    public void MemberInit() {
        initService.MemberInit();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class initService {

        private final MemberRepository memberRepository;
        private final PasswordEncoder encoder;

        public void MemberInit() {
            List<Member> members = IntStream.range(1, 51)
                    .mapToObj(i -> {
                        return Member.builder()
                                .email("test" + i + "@petopia.com")
                                .name("petopia" + i)
                                .account("ptAccount" + i)
                                .password(encoder.encode("petopia123"))
                                .build();
                    })
                    .toList();
            memberRepository.saveAll(members);
        }
    }

}
