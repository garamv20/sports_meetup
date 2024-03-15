package com.zero.sports_meetup.dto;

import com.zero.sports_meetup.domain.User;
import com.zero.sports_meetup.type.UserRole;
import lombok.Data;

public class Auth {
  @Data
  public static class SignIn{
    private String emailId;
    private String password;
  }

  @Data
  public static class SignUp {
    private String emailId;
    private String password;
    private String name;
    private String region;

    public User toUserEntity() {
      return User.builder()
          .emailId(this.emailId)
          .password(this.password)
          .name(this.name)
          .region(this.region)
          .userRole(UserRole.USER)
          .build();
    }
  }
}
