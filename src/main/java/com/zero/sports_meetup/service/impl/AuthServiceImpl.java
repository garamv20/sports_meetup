package com.zero.sports_meetup.service.impl;

import com.zero.sports_meetup.dto.MailRequest;
import com.zero.sports_meetup.dto.UserDto;
import com.zero.sports_meetup.exception.UserException;
import com.zero.sports_meetup.type.ErrorCode;
import com.zero.sports_meetup.component.MailComponent;
import com.zero.sports_meetup.component.RedisComponent;
import com.zero.sports_meetup.domain.User;
import com.zero.sports_meetup.dto.Auth.SignUp;
import com.zero.sports_meetup.repository.UserRepository;
import com.zero.sports_meetup.service.AuthService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final MailComponent mailComponent;
  private final PasswordEncoder passwordEncoder;
  private final RedisComponent redisComponent;

  public static final String AUTH_SUCCESS = "AuthSuccess";

  @Override
  public void sendAuthMail(String email) {
    checkExistsEmail(email);
    String uuid = UUID.randomUUID().toString().substring(0, 6);

    String subject = "sports meetup 메일 인증 코드 입니다.";
    String text = "<p>이메일 인증 코드입니다. 해당 코드를 사이트로 돌아가 입력해 주세요.</p>"
        + "<div>CODE : "+ uuid +"</div>";

    // 인증 메일 전송
    mailComponent.sendMail(email, subject, text);

    // redis 데이터 저장 (만료 10분)
    redisComponent.saveEmailVerificationCode(email, uuid, 10);
  }

  @Override
  public boolean verifyMail(MailRequest request) {
    String code = redisComponent.getData(request.getEmail());
    if (!code.equals(request.getAuthCode())) {
      throw new UserException(ErrorCode.AUTH_CODE_UN_MATCH);
    }

    // 인증완료 redis 저장
    redisComponent.saveEmailVerificationCode(request.getEmail(), AUTH_SUCCESS, 300);
    return true;
  }

  @Override
  public UserDto signUp(SignUp signUp) {
    checkExistsEmail(signUp.getEmailId());

    // 이메일 인증 확인
    String checkAuth = redisComponent.getData(signUp.getEmailId());
    if (!checkAuth.equals(AUTH_SUCCESS)) {
      throw new UserException(ErrorCode.NOT_AUTH_EMAIL);
    }

    String encryptPassword = passwordEncoder.encode(signUp.getPassword());

    User user = signUp.toUserEntity();

    var result = this.userRepository.save(user);

    return UserDto.fromEntity(result);
  }

  /* 이메일 중복 체크 */
  private void checkExistsEmail(String email) {
    if (this.userRepository.existsByEmailId(email)) {
      throw new UserException(ErrorCode.ALREADY_EMAIL_EXIST);
    }
  }
}
