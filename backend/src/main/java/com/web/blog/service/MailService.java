package com.web.blog.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

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

    public boolean mailSend(User user, String key) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setFrom(FROM_ADDRESS);
            message.setSubject(TITLE);

            StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html>");
			sb.append("<head>");
			sb.append("<meta charset=utf-8/>");
			sb.append("<title>Be my Family</title>");
			sb.append("</head>");
			sb.append("<body style=\"font-family: Arial, '맑은 고딕', 'Malgun Gothic', Dotum, '돋움',sans-serif, Helvetica; font-size:12px; color:#464646; line-height:0;\">");
			sb.append("<div style=\"width:100%; padding:20px 0;\">");
			sb.append("<div style=\"width:600px font-size:12px; color:#636363; background-color:#f4f4f4; line-height:1.3em; padding:20px 30px; margin-top:10px;\">");
			sb.append("<div style=\"font-size:13px padding:30px; text-align:center;\">");
            sb.append("<p>유기견의 가족을 찾아주는 BE MY FAMILY입니다.</p>");
            sb.append("<br />");
            sb.append("<p style=\"line-height:1.6em;\">" + user.getUid() + "(" + user.getEmail() + ")" + "님 인증코드를 입력해주세요.</p>");
            sb.append("<br />");
			sb.append("<div style=\"background:#f2cc59; border:1px solid black; padding:5px 5px 5px 5px; margin-bottom:10px;\">");
			sb.append("<p style=\"font-size:17px line-height:1.6em; color:blue;\">");
			sb.append("인증 코드 : " + key);
			sb.append("</p>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("<div style=\"width:600px font-size:12px; color:#636363;line-height:1.3em; padding:20px 30px; margin-top:10px; text-align:center;\">");
			sb.append("저희 사이트를 이용해 주셔서 감사합니다.<br /> 다른 궁금하신 사항은  웹사이트(<a href=\"http://i3b201.p.ssafy.io/\" target=\"_blank\">www.bemyfamily.co.kr</a>)에서 확인해 주세요.");
			sb.append("<br />");
			sb.append("Copyrjght 2020 Dogker All rights reserved.");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</body>");
			sb.append("</html>");

            message.setContent(sb.toString(), "text/html; charset=utf-8");

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