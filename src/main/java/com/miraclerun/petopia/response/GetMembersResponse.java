package com.miraclerun.petopia.response;

import com.miraclerun.petopia.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GetMembersResponse {

    private int totalPages;
    private List<MemberDto> members;

    public GetMembersResponse() {
    }

    @Builder
    public GetMembersResponse(int totalPages, List<MemberDto> members) {
        this.totalPages = totalPages;
        this.members = members;
    }
}
