package com.web.blog.model.lost;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;

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
    private Integer lostno;

    private String losttype;
    private String lostbreed;
    private String lostsex;
    private String lostage;
    private Date lostdate;
    private String lostsido;
    private String lostgugun;
    private String lostplace;
    private String lostcontent;
    private String losttagtext;
    private String lostphone;
    private String lostpic1;
    private String lostpic2;
    private String lostpic3;

    @Column(insertable = false, updatable = false)
    private Date lostcreatedate;
}