package com.web.blog.model.care;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Survey {
    
    @Id
	private String uid;

	private String name;
	private String email;
	private String phone;
	private String job;
	private int marriaged;
	private int sex;
	private Date birthdate;
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

}