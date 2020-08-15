package com.web.blog.service;

import java.util.Optional;

import com.web.blog.model.care.CareRecommend;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;

import org.springframework.stereotype.Service;

@Service
public class Fit {

    //  사용자 설문 기준 최소 적합 부적합 영역
    public int proper(Survey survey) {

        int no = 2;
        int result = 1;

        if  (!survey.getNation().equals("korea")) {
            result = no;
        } else if  (survey.getPlace().equals("business")) {
            result = no;
        } else if  (survey.getPresentanimal() >= 6) {
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
        }

        return result;
    }

    //  설문 적합 사용자 기반으로 강아지 추천 영역
    public static boolean recommend (CareRecommend careRecommend, Optional<Survey> survey) {

        boolean ok = true;
        boolean no = false;
        boolean result = false;
 
        String house = survey.get().getHouse();
        String kind = careRecommend.getKindcd();
        String weight = careRecommend.getWeight();
        double kg = Double.parseDouble(weight.replace("(Kg)", ""));
        
        if (house.equals("room") || house.equals("apt") || house.equals("villa")) {
            //종
            if(kind.contains("꼬똥 드 뚤레아") || kind.contains("말티즈") || kind.contains("퍼그") || kind.contains("토이 푸들") ||
            kind.contains("포메라니안") || kind.contains("푸들") || kind.contains("시츄") || kind.contains("요크셔 테리어") ||
            kind.contains("치와와") || kind.contains("비숑 프리제")) {
                result = ok;
            }

            if(kg < 5) {
                result = ok;
            }

        } else {

        }

        //거주 형태
        //거주 지역
        //place


        return result;
    }

}