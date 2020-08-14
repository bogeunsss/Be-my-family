package com.web.blog.service;

import com.web.blog.model.care.Survey;

import org.springframework.stereotype.Service;

@Service
public class Fit {

    public int proper(Survey survey) {

        int no = 2;
        int result = 1;

        //적합 부적합 영역
        if(!survey.getNation().equals("korea")) {
            result = no;
        } else if(survey.getPlace().equals("business")) {
            result = no;
        }

        return result;
    }

    //강아지 추천 영역
    public String recommend () {

        return " ";
    }


}