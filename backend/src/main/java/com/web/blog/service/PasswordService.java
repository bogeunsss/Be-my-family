package com.web.blog.service;

import java.util.Optional;
import java.util.Random;

import com.web.blog.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "popom.m99@gmail.com";
    private static final String TITLE = "Be my Family 비밀 번호 찾기 입니다.";
    private static final String CONTEXT = "님 임시로 생성된 비밀번호 : ";

    public boolean mailSend(Optional<User> user, String key) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.get().getEmail());
            message.setFrom(FROM_ADDRESS);
            message.setSubject(TITLE);
            message.setText(user.get().getUid() + "(" + user.get().getEmail() + ")" + CONTEXT + key);
            System.out.println(message.getSubject());
            System.out.println(message.getFrom());
            System.out.println(message.getText());
            System.out.println(message.getTo());
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String makePassword(int len) {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        int idx = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            idx = (int) (charSet.length * Math.random());
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }

}