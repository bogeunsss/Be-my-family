package com.web.blog.model.postscript;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Valid
@Data
public class CommentRequest {
    
    @ApiModelProperty(required = false)
    private int commentno;

    @ApiModelProperty(required = true)
    private int postscriptno;

    @ApiModelProperty(required = true)
    private String uid;
    
    @ApiModelProperty(required = true)
    private String content;

}