package com.web.blog.service;

import java.util.Optional;

import com.web.blog.model.care.CareRecommend;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;

import org.springframework.stereotype.Service;

@Service
public class Fit {

    // 사용자 설문 기준 최소 적합 부적합 영역
    public int proper(Survey survey) {

        int no = 2;
        int result = 1;

        if (!survey.getNation().equals("korea")) {
            result = no;
        } else if (survey.getPlace().equals("business")) {
            result = no;
        } else if (survey.getPresentanimal() >= 6) {
            result = no;
        } else if (survey.getFamilyunder() >= 2) {
            result = no;
        } else if (survey.getFamilymiddle() >= 3) {
            result = no;
        } else if (survey.getFamilyagree() == 0) {
            result = no;
        } else if (survey.getDissolution() == 2) {
            result = no;
        } else if (survey.getSickness() == 1) {
            result = no;
        } else if (survey.getAlone() > 9) {
            result = no;
        }

        return result;
    }

    // 설문 적합 사용자 기반으로 강아지 추천 영역
    public static boolean recommend (CareRecommend careRecommend, Optional<Survey> survey) {

        boolean ok = true;
        boolean no = false;
        boolean result = false;
 
        //강아지 정보
        String kind = careRecommend.getKindcd();
        String weight = careRecommend.getWeight();
        double kg = Double.parseDouble(weight.replace("(Kg)", ""));
        
        //설문 정보
        String house = survey.get().getHouse();
        String place = survey.get().getPlace();
        
        if (house.equals("room")) {
            //종
            if(kind.contains("꼬똥") || kind.contains("말티즈") || kind.contains("퍼그") || kind.contains("믹스") ||
            kind.contains("포메라니안") || kind.contains("푸들") || kind.contains("시츄") || kind.contains("요크셔") ||
            kind.contains("치와와") || kind.contains("비숑") || kind.contains("빠삐용")) {
                result = ok;
            }

            //몸무게
            if(kg > 7) {
                result = no;
            }

        } else if (house.equals("apt") || house.equals("villa")) {
            if(kind.contains("꼬똥") || kind.contains("말티즈") || kind.contains("퍼그") || kind.contains("믹스") ||
            kind.contains("포메라니안") || kind.contains("푸들") || kind.contains("시츄") || kind.contains("요크셔") ||
            kind.contains("치와와") || kind.contains("비숑") || kind.contains("빠삐용") || kind.contains("슈나") || 
            kind.contains("닥스훈트") || kind.contains("시바") || kind.contains("스피츠") || kind.contains("코기") || 
            kind.contains("스파니엘") || kind.contains("프렌치 불독") || kind.contains("삽살") || kind.contains("핀셔")) {
                result = ok;
            }

            if(kg > 15) {
                result = no;
            }

        } else if (house.equals("housing") && place.equals("home")) {
            if(kind.contains("꼬똥") || kind.contains("말티즈") || kind.contains("퍼그") || kind.contains("믹스") ||
            kind.contains("포메라니안") || kind.contains("푸들") || kind.contains("시츄") || kind.contains("요크셔") ||
            kind.contains("치와와") || kind.contains("비숑") || kind.contains("슈나") || kind.contains("닥스훈트")
            || kind.contains("시바") || kind.contains("스피츠") || kind.contains("코기") || kind.contains("스파니엘")
            || kind.contains("프렌치 불독") || kind.contains("리트리버") || kind.contains("콜리") || kind.contains("삽살") || kind.contains("핀셔")
            || kind.contains("진도") || kind.contains("풍산") || kind.contains("제주") || kind.contains("쉽독") || kind.contains("버니즈")) {
                result = ok;
            }
        }

        return result;
    }

}