package com.web.blog.controller.care;

import java.util.List;

import com.web.blog.dao.care.CareDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.Careboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
public class CareController {
    
    @Autowired
    CareDao careDao;

    @GetMapping("/care/search")
    @ApiOperation(value = "보호소 유기견 검색")
    public Object careboardSearch(@RequestParam(required = true) final String category,
            @RequestParam(required = true) final String searchText, @RequestParam(required = true) int pageno) {
        
        ResponseEntity response = null;
        Page<Careboard> careOpt = null;

        if(category.equals("careAddr")) {
            careOpt = careDao.findByCareaddrContaining(searchText, PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));
        }else if (category.equals("kindCd")) {
            careOpt = careDao.findBykindcdContaining(searchText, PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));
        }
         
        final BasicResponse result = new BasicResponse();

        if (careOpt!=null) {
            result.status = true;
            result.data = "success";
            result.object = careOpt;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    
        return response;
    } 

    @GetMapping("/care/list")
    @ApiOperation(value = "보호소 유기견 조회")
    public Object careboardList(@RequestParam(required = true) int pageno) {
        
        ResponseEntity response = null;
        Page<Careboard> careList = null;
         
        final BasicResponse result = new BasicResponse();

        careList = careDao.findAll(PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));

        if (careList != null) {
            result.status = true;
            result.data = "success";
            result.object = careList;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    
        return response;
    } 
}