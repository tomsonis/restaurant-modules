package com.beben.tomasz.restaurant.standalone.mail;

import com.beben.tomasz.restaurant.commons.mail.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@AllArgsConstructor
public class AsyncEmailService implements EmailService {

    private JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(String to, String subject, String text) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error on sending email {}", e.getMessage());
        }
    }

}
