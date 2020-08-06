package com.web.blog.model.lost;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lostno;

    private String losttype;
    private String lostbreed;
    private String lostsex;
    private String lostage;
    private String lostdate;
    private String lostsido;
    private String lostgugun;
    private String lostplace;
    private String lostcontent;
    private String losttagtext;
    private String lostphone;
    private String lostpic1;
    private String lostpic2;
    private String lostpic3;
    private String uid;

    @Column(insertable = false, updatable = false)
    private Date lostcreatedate;
}