package com.miraclerun.petopia;

import com.miraclerun.petopia.domain.Member;
import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.repository.PetRepository;
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
        initService.PetInit();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class initService {

        private final MemberRepository memberRepository;
        private final PetRepository petRepository;
        private final PasswordEncoder encoder;

        public void MemberInit() {
//            List<Member> members = IntStream.range(1, 51)
//                    .mapToObj(i -> {
//                        return Member.builder()
//                                .email("test" + i + "@petopia.com")
//                                .name("petopia" + i)
//                                .account("ptaccount" + i)
//                                .password(encoder.encode("petopia123"))
//                                .build();
//                    })
//                    .toList();
//            memberRepository.saveAll(members);
            Member member = Member.builder()
                    .email("test@test.com")
                    .name("미라클")
                    .account("test")
                    .password(encoder.encode("test1234"))
                    .build();
            memberRepository.save(member);
        }

        public void PetInit() {
            List<Pet> pets = IntStream.range(1, 11)
                    .mapToObj(i -> {
                        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
                        return Pet.builder()
                                .member(member)
                                .name("펫" + i)
                                .intro("안녕하세요" + i)
                                .build();
                    })
                    .toList();
            petRepository.saveAll(pets);
        }
    }

}
