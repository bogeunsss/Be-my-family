package com.web.blog.model.lost;

import java.util.Date;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Valid
@Data
public class LostRequest {

    @ApiModelProperty(required = false)
    private Integer lostno;

    @ApiModelProperty(required = true)
    private String losttype;

    @ApiModelProperty(required = false)
    private String lostbreed;

    @ApiModelProperty(required = false)
    private String lostsex;

    @ApiModelProperty(required = false)
    private String lostage;

    @ApiModelProperty(required = true)
    private Date lostdate;

    @ApiModelProperty(required = true)
    private String sido;

    @ApiModelProperty(required = true)
    private String gugun;

    @ApiModelProperty(required = true)
    private String lostplace;

    @ApiModelProperty(required = false)
    private String lostcontent;

    @ApiModelProperty(required = false)
    private String losttagtext;

    @ApiModelProperty(required = false)
    private String lostphone;

    @ApiModelProperty(required = false)
    private Date lostcreatedate;

}