package com.zero.sports_meetup.repository;

import com.zero.sports_meetup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByEmailId(String emailId);
}
