package com.zero.sports_meetup.component;


import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisComponent {

  private final RedisTemplate<String, String> redisTemplate;

  public void saveEmailVerificationCode(String email, String verificationCode, int minutes) {
    redisTemplate.opsForValue().set(email, verificationCode, Duration.ofMinutes(minutes));
  }

  public String getData(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public void deleteData(String key) {
    redisTemplate.delete(key);
  }

}
