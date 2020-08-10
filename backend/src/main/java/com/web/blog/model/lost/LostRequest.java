package com.web.blog.model.lost;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Valid
@Data
public class LostRequest {

    @ApiModelProperty(required = true)
    private String uid;

    @ApiModelProperty(required = false)
    private int lostno;

    @ApiModelProperty(required = true)
    private String losttype;

    @ApiModelProperty(required = false)
    private String lostbreed;

    @ApiModelProperty(required = false)
    private String lostsex;

    @ApiModelProperty(required = false)
    private String lostage;

    @ApiModelProperty(required = true)
    private String lostdate;

    @ApiModelProperty(required = true)
    private String lostsido;

    @ApiModelProperty(required = true)
    private String lostgugun;

    @ApiModelProperty(required = true)
    private String lostplace;

    @ApiModelProperty(required = false)
    private String lostcontent;

    @ApiModelProperty(required = false)
    private List<String> losttagtext;

    @ApiModelProperty(required = false)
    private String lostphone;

    @ApiModelProperty(required = false)
    private Date lostcreatedate;

}