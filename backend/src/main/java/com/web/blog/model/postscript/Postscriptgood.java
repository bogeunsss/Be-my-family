package com.web.blog.model.postscript;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;


import io.swagger.annotations.ApiModelProperty;


@Entity
@Valid
@Data
public class Postscriptgood {
    @Id
    private int postscriptgoodno;

    @ApiModelProperty(required = true)
    private int postscriptno;
    private String uid;
}