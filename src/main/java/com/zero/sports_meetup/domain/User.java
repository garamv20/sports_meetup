package com.zero.sports_meetup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.sports_meetup.type.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String emailId;

  @JsonIgnore
  private String password;

  private String name;

  private String region;

  private String refreshToken;

  @Enumerated(EnumType.STRING)
  private UserRole userRole;

}
