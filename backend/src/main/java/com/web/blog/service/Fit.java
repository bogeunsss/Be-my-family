package com.web.blog.service;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;

import org.springframework.stereotype.Service;

@Service
public class Fit {

    //사용자 설문 기준 최소 적합 부적합 영역
    public int proper(Survey survey) {

        int no = 2;
        int result = 1;

        if(!survey.getNation().equals("korea")) {
            result = no;
        } else if(survey.getPlace().equals("business")) {
            result = no;
        } else if(survey.getPresentanimal() >= 6) {
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

    //설문 적합 사용자 기반으로 강아지 추천 영역
    public Boolean recommend (Careboard dog, Survey survey) {

        Boolean ok = true;
        Boolean result = false;

        //강아지 kg
        //강아지 종
        //거주 형태

        return result;
    }

}