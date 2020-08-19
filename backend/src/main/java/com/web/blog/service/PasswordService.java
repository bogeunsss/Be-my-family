package com.web.blog.service;

import java.util.Optional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

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

    public boolean mailSend(Optional<User> user, String key) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.addRecipient(RecipientType.TO, new InternetAddress(user.get().getEmail()));
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
            sb.append("<p style=\"line-height:1.6em;\">" + user.get().getUid() + "(" + user.get().getEmail() + ")" + "님 임시 비밀번호를 생성하였습니다. 임시 비밀번호로 로그인 하신 후 비밀번호를 변경해주세요.</p>");
            sb.append("<br />");
			sb.append("<div style=\"background:#f2cc59; border:1px solid black; padding:5px 5px 5px 5px; margin-bottom:10px;\">");
			sb.append("<p style=\"font-size:17px line-height:1.6em; color:blue;\">");
			sb.append("임시 비밀번호 : " + key);
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