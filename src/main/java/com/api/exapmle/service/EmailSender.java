package com.api.exapmle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String name, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("admin@yourcompany.com"); // admin email
        message.setSubject("New User Registration");
        message.setText(
                "A new user has registered.\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email
        );

        mailSender.send(message);
    }
}
