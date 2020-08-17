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

    private String age;
    private String careaddr;
    private String carenm;
    private String caretel;
    private String chargenm;
    private String colorcd;
    @Id
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
}
