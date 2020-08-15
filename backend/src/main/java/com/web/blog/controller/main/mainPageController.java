package com.web.blog.controller.main;

import java.util.List;

import com.web.blog.dao.main.MainCareDao;
import com.web.blog.dao.main.MainLostDao;
import com.web.blog.dao.main.MainPostscriptDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.MainpageResponse;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.lost.Lost;
import com.web.blog.model.postscript.Postscript;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
                @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
                @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
                @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
public class mainPageController {

    @Autowired
    MainCareDao mainCareDao;

    @Autowired
    MainLostDao mainLostDao;

    @Autowired
    MainPostscriptDao mainPostscriptDao;

    @GetMapping("/mainpage")
    @ApiOperation(value = "메인페이지")
    public Object Mainpage() {

        ResponseEntity response = null;
        final MainpageResponse result = new MainpageResponse();
        
        List<Careboard> careboards = mainCareDao.findTop4ByOrderByNoticeedtDesc();
        List<Lost> losts = mainLostDao.findTop4ByOrderByLostnoDesc();
        List<Postscript> postscripts = mainPostscriptDao.findTop4ByOrderByPostscriptnoDesc();

        result.data = "success";
        result.status = true;
        result.careList = careboards;
        result.lostList = losts;
        result.postscriptList = postscripts;
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

}