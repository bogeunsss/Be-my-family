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
    private String sido;
    private String gugun;
    private String lostplace;
    private String lostcontent;
    private String lostphone;

    @Column(insertable = false, updatable = false)
    private Date lostcreatedate;
}