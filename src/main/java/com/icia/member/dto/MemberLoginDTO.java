package com.icia.member.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDTO {
    @NotBlank(message = "로그인시 이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank
    @Length(min = 2, max = 8, message = "비밀번호는 2~8자로 입력")
    private String memberPassword;



}
