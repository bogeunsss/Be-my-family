package com.web.blog.model.adoption;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@ToString
public class ApplicationRequest {

    @NotNull
    String uid;
    // @NotNull
    // String mid;
    @NotNull
    String desertionno;
    @NotNull
    String fixdate;
    @NotNull
    String fixtime;


    // public String getMid() {
    //     return mid;
    // }

    // public void setMid(String mid) {
    //     this.mid = mid;
    // }

    public String getDesertionno() {
        return desertionno;
    }

    public void setDesertionno(String desertionno) {
        this.desertionno = desertionno;
    }

    public String getFixdate() {
        return fixdate;
    }

    public void setFixdate(String fixdate) {
        this.fixdate = fixdate;
    }

    public String getFixtime() {
        return fixtime;
    }

    public void setFixtime(String fixtime) {
        this.fixtime = fixtime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
}