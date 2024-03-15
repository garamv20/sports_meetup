package com.zero.sports_meetup.util;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailUtil {

  private final JavaMailSender mailSender;

  public boolean sendMail(String mail, String subject, String text) {

    boolean result = false;

    MimeMessagePreparator msg = new MimeMessagePreparator() {
      @Override
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(mail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
      }
    };

    try {
      mailSender.send(msg);
      result = true;

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return result;
  }

}
