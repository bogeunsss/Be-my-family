package com.web.blog.service;
import com.web.blog.model.manager.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ManagerMailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "popom.m99@gmail.com";
    private static final String TITLE = "김민주짱에 가입한 것을 환영합니다.";
    private static final String CONTEXT = "님의 회원가입을 축하드립니다.";

    public boolean managerMailSend(Manager manager) {//manager로 변경

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(manager.getEmail());
            message.setFrom(FROM_ADDRESS);
            message.setSubject(TITLE);
            message.setText(manager.getMid() + "(" + manager.getEmail() + ")" + CONTEXT);
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
}