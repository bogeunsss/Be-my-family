package com.web.blog.model;

import io.swagger.annotations.ApiModelProperty;

public class CareboardResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "object", position = 3)
    public Object object;
	public int totalPage;
	public boolean hasNext;
	public long totalData;
	public int currentPage;
	public int currentData;
}