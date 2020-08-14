package com.web.blog.model.care;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Careboard {

    @Id
    private String desertionno;

    private String noticeedt;
    private String popfile;
    private String processstate;
    private String sexcd;
    private String neuteryn;
    private String specialmark;
    private String carenm;
    private String caretel;
    private String careaddr;
    private String orgnm;
    private String chargenm;
    private String officetel;
    private String noticecomment;
    private String numofrows;
    private String pageno;
    private String totalcount;
    private String resultcode;
    private String resultmsg;
    private String filename;
    private String happendt;
    private String happenplace;
    private String kindcd;
    private String colorcd;
    private String age;
    private String weight;
    private String noticeno;
    private String noticesdt;
}
