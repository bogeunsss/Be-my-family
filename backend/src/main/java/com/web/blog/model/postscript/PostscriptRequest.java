package com.web.blog.model.postscript;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Valid
@Data
public class PostscriptRequest {
    
    @ApiModelProperty(required = true)
    String uid;
    
    @ApiModelProperty(required = true)
    String title;
    
    @ApiModelProperty(required = true)
    String content;
   
    @ApiModelProperty(required = false)
    String image;
    String sido;
    String gugun;
    String kind;
    int postscriptno;
}
