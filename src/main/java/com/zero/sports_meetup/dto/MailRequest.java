package com.zero.sports_meetup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailRequest {

  @NotBlank(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식에 맞게 입력해주세요.")
  private String email;

  @NotBlank(message = "인증번호를 입력해주세요.")
  private String authCode;
}
