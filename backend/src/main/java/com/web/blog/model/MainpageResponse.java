package com.web.blog.model;

import java.util.List;

import com.web.blog.model.postscript.Postpic;

import io.swagger.annotations.ApiModelProperty;

public class MainpageResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    public Object careList;
    public Object lostList;
    public Object postscriptList;
	public List<Postpic> postpic;
}