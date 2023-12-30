package com.miraclerun.petopia.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberRequest {

    @Email(message = "이메일 형식이 맞지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "성명을 입력해주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 12, message = "아이디는 4자 이상, 12자 이하로 입력해주세요.")
    private String account;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    public CreateMemberRequest() {
    }

    @Builder
    public CreateMemberRequest(String email, String name, String account, String password) {
        this.email = email;
        this.name = name;
        this.account = account;
        this.password = password;
    }
}
