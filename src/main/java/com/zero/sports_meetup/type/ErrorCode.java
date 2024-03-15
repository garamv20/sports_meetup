package com.zero.sports_meetup.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
  INVALID_REQUEST("잘못된 요청입니다."),
  ALREADY_EMAIL_EXIST("이미 존재하는 메일계정입니다."),
  AUTH_CODE_UN_MATCH("잘못된 인증 코드 입니다."),
  NOT_AUTH_EMAIL("메일계정이 인증되지 않았습니다.")
  ;
  private final String description;
}
