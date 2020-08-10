package com.web.blog.model;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "object", position = 3)
    public Object object;
    public Object objectsurvey;
	public String email;
	public String uid;
    public String password;
    public boolean interest;
    public String name;
    public Object tag;
    public boolean adoption;
    public Object madetag;
    public Object match;
    public Object lostReply;
}
