package com.web.blog.model.adoption;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@ToString
public class ApplicationRequest {


    //사용자 정보
    @ApiModelProperty(required = true)
    @NotNull
    String uid;
    @ApiModelProperty(required = true)
    @NotNull
    String name;
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    @ApiModelProperty(required = true)
    @NotNull
    String phone;
    String job;
    @ApiModelProperty(required = true)
    @NotNull
    int marriaged;
    @ApiModelProperty(required = true)
    @NotNull
    int sex;

    //관리자
    @ApiModelProperty(required = true)
    @NotNull
    String mid;

    //설문정보
    String nation;
    String sido;
    String gugun;
    String place;
    boolean beforeover;
    boolean beforeadopt;
    int presentanimal;
    int familyunder;
    int familymiddle;
    boolean familyagree;
    int dissolution;
    boolean sickness;
    int alone;
    String temp;
    String house;
    int eatmoney;
    int caremoney;
    String reason;
    String think;

    //유기견 number
    @ApiModelProperty(required = true)
    @NotNull
    String desertionno;
    
    //상담날짜
    @NotNull
    String fixdate;
    @NotNull
    String fixtime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMarriaged() {
        return marriaged;
    }

    public void setMarriaged(int marriaged) {
        this.marriaged = marriaged;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getGugun() {
        return gugun;
    }

    public void setGugun(String gugun) {
        this.gugun = gugun;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isBeforeover() {
        return beforeover;
    }

    public void setBeforeover(boolean beforeover) {
        this.beforeover = beforeover;
    }

    public boolean isBeforeadopt() {
        return beforeadopt;
    }

    public void setBeforeadopt(boolean beforeadopt) {
        this.beforeadopt = beforeadopt;
    }

    public int getPresentanimal() {
        return presentanimal;
    }

    public void setPresentanimal(int presentanimal) {
        this.presentanimal = presentanimal;
    }

    public int getFamilyunder() {
        return familyunder;
    }

    public void setFamilyunder(int familyunder) {
        this.familyunder = familyunder;
    }

    public int getFamilymiddle() {
        return familymiddle;
    }

    public void setFamilymiddle(int familymiddle) {
        this.familymiddle = familymiddle;
    }

    public boolean isFamilyagree() {
        return familyagree;
    }

    public void setFamilyagree(boolean familyagree) {
        this.familyagree = familyagree;
    }

    public int getDissolution() {
        return dissolution;
    }

    public void setDissolution(int dissolution) {
        this.dissolution = dissolution;
    }

    public boolean isSickness() {
        return sickness;
    }

    public void setSickness(boolean sickness) {
        this.sickness = sickness;
    }

    public int getAlone() {
        return alone;
    }

    public void setAlone(int alone) {
        this.alone = alone;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getEatmoney() {
        return eatmoney;
    }

    public void setEatmoney(int eatmoney) {
        this.eatmoney = eatmoney;
    }

    public int getCaremoney() {
        return caremoney;
    }

    public void setCaremoney(int caremoney) {
        this.caremoney = caremoney;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getThink() {
        return think;
    }

    public void setThink(String think) {
        this.think = think;
    }

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
    
}