package com.zero.sports_meetup.service;

import com.zero.sports_meetup.domain.User;
import com.zero.sports_meetup.dto.Auth;
import com.zero.sports_meetup.dto.MailRequest;
import com.zero.sports_meetup.dto.UserDto;

public interface AuthService {

  /* 이메일 인증코드 전송 */
  void sendAuthMail(String email);

  /* 이메일 인증코드 검증 */
  boolean verifyMail(MailRequest request);

  /* 회원 가입 */
  UserDto signUp(Auth.SignUp signUp);




}
