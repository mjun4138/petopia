package com.miraclerun.petopia.service;

import com.miraclerun.petopia.domain.Pet;
import com.miraclerun.petopia.repository.MemberRepository;
import com.miraclerun.petopia.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PetServiceTest {
    @Autowired private MemberRepository memberRepository;
    @Autowired private PetRepository petRepository;
    @Autowired private PetService petService;

    @Test
    @DisplayName("회원별 펫 조회")
    public void 회원별_펫_조회() throws Exception {
        //given

        //when
        List<Pet> pets = petService.petsByMember(1L);
        //then
        Assertions.assertEquals(1L, pets.size());
     }
}
