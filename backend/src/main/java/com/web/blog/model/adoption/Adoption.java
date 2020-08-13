package com.web.blog.model.adoption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Adoption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adoptionno;

    //사용자 정보
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String job;
    private int marriaged;
    private int sex;

    private String mid;

    //설문 정보
    private String nation;
    private String sido;
    private String gugun;
    private String place;
    private int beforeover;
    private int beforeadopt;
    private int presentanimal;
    private int familyunder;
    private int familymiddle;
    private int familyagree;
    private int dissolution;
    private int sickness;
    private int alone;
    private String temp;
    private String house;
    private int eatmoney;
    private int caremoney;
    private String reason;
    private String think;

    //유기견 number
    private String desertionno;

    //상담날짜
    private String fixdate;
    private String fixtime;

    private int state;
}