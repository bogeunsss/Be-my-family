package com.web.blog.model.lost;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Valid
@Data
public class LostreplyRequest {


    @ApiModelProperty(required = false)
    private Integer lostreplyno;

    @ApiModelProperty(required = true)
    private String uid;

    @ApiModelProperty(required = true)
    private Integer lostno;

    @ApiModelProperty(required = true)
    private String lostcontent;

}