package com.zero.sports_meetup.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.sports_meetup.domain.User;
import com.zero.sports_meetup.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{

  private Long id;

  private String emailId;

  @JsonIgnore
  private String password;

  private String name;

  private String region;

  private String refreshToken;

  private UserRole userRole;

  public static UserDto fromEntity(User user) {
    return com.zero.sports_meetup.dto.UserDto.builder()
        .id(user.getId())
        .emailId(user.getEmailId())
        .password(user.getPassword())
        .name(user.getName())
        .region(user.getRegion())
        .refreshToken(user.getRefreshToken())
        .userRole(user.getUserRole())
        .build();
  }
}
