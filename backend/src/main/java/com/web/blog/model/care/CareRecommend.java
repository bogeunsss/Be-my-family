package com.web.blog.model.care;

import lombok.Data;
import javax.validation.Valid;


@Valid
@Data
public class CareRecommend {

    private String age;
    private String careaddr;
    private String carenm;
    private String caretel;
    private String chargenm;
    private String colorcd;
    private String desertionno;
    private String filename;
    private String happendt;
    private String happenplace;
    private String kindcd;
    private String neuteryn;
    private String noticeedt;
    private String noticeno;
    private String noticesdt;
    private String officetel;
    private String orgnm;
    private String popfile;
    private String processstate;
    private String sexcd;
    private String specialmark;
    private String weight;

    private boolean recommend;
}
