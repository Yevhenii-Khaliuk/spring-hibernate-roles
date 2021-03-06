package com.khaliuk.service;

import com.khaliuk.model.User;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Override
    public void send(User user) {
        SimpleMailMessage msg = getAccountVerifyMail(user);
        CompletableFuture.runAsync(() -> mailSender.send(msg));
    }

    private SimpleMailMessage getAccountVerifyMail(User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Mate Academy Application Email Verification");
        msg.setText(String.format(
                "Hi %s!\n Please follow the link to verify email: http://%s/%s/%s",
                user.getFirstName(),
                env.getProperty("host"),
                "email-verification",
                user.getToken()));
        return msg;
    }
}
