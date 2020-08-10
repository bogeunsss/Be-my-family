package com.web.blog.model.postscript;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Postscript {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postscriptno", columnDefinition = "Int")
    private int postscriptno;

    private String uid;
    private String title;
    private String content;
    private String image;
    private String sido;
    private String gugun;
    private String kind;

    @Column(insertable = false, updatable = false)
    private Date createdate;
}