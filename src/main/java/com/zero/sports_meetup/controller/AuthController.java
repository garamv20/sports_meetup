package com.zero.sports_meetup.controller;

import com.zero.sports_meetup.dto.Auth;
import com.zero.sports_meetup.dto.MailRequest;
import com.zero.sports_meetup.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  /**
   * 이메일 확인을 위한 인증메일 전송
   */
  @PostMapping("/mail/send")
  public ResponseEntity<?> sendAuthMail(@RequestBody MailRequest request) {
    authService.sendAuthMail(request.getEmail());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 이메일 인증코드 검증
   */
  @PostMapping("/mail/verify")
  public ResponseEntity<?> VerifyEmail(@RequestBody MailRequest request) {
    return ResponseEntity.ok(authService.verifyMail(request));
  }

  /**
   * 회원 가입
   */
  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody Auth.SignUp request) {
    return ResponseEntity.ok( authService.signUp(request));
  }


}
