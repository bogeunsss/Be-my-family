package com.web.blog.service;

import java.util.Random;

import com.web.blog.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "popom.m99@gmail.com";
    private static final String TITLE = "Be my Family에 가입한 것을 환영합니다.";
    private static final String CONTEXT = "님의 회원가입을 축하드립니다. /n 인증 코드를 입력해주세요. /n 인증코드 : ";

    public boolean mailSend(User user, String key) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setFrom(FROM_ADDRESS);
            message.setSubject(TITLE);
            message.setText(user.getUid() + "(" + user.getEmail() + ")" + CONTEXT + key);
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

    public String makekey(int len, int dupCd) {
        
        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수
        
        for(int i=0;i<len;i++) {
            
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            
            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        return numStr;
    }


}