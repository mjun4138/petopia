package com.miraclerun.petopia;

import com.miraclerun.petopia.domain.*;
import com.miraclerun.petopia.exception.MemberNotFound;
import com.miraclerun.petopia.exception.PetNotFound;
import com.miraclerun.petopia.repository.FeedRepository;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.repository.PetRepository;
import com.miraclerun.petopia.repository.PetUploadRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final initService initService;

    @PostConstruct
    public void MemberInit() {
        initService.MemberInit();
        initService.PetInit();
        initService.FeedInit();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class initService {

        private final MemberRepository memberRepository;
        private final PetRepository petRepository;
        private final FeedRepository feedRepository;
        private final PetUploadRepository petUploadRepository;
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
            Member member = memberRepository.findById(1L).orElseThrow(MemberNotFound::new);
            for (int i = 1; i < 4; i++) {
                Pet pet = Pet.builder()
                        .member(member)
                        .name("펫" + i)
                        .intro("안녕하세요" + i)
                        .build();
                petRepository.save(pet);
                PetUpload petUpload = PetUpload.builder()
                        .fileName("pet" + i + ".jpg")
                        .filePath("/uploads/" + "pet" + i + ".jpg")
                        .fileSize(1000L)
                        .pet(pet)
                        .build();
                petUploadRepository.save(petUpload);
            }
        }

        public void FeedInit() {
            List<Upload> uploads = IntStream.range(1, 5)
                    .mapToObj(i -> {
                        return Upload.builder()
                                .fileName("feed" + i + ".jpg")
                                .filePath("/uploads/" + "feed" + i + ".jpg")
                                .fileSize(1000L)
                                .build();
                    })
                    .collect(Collectors.toList());

            Pet pet = petRepository.findById(1L).orElseThrow(PetNotFound::new);

            Feed feed = Feed.builder()
                    .content("게시글입니다.")
                    .uploads(uploads)
                    .pet(pet)
                    .build();
            feedRepository.save(feed);


        }
    }

}
