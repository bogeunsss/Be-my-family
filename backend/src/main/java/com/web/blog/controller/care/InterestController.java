package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import com.web.blog.dao.care.CareDao;
import com.web.blog.dao.care.InterestDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.Care;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class InterestController {
   
    @Autowired
    InterestDao interestDao;

    @GetMapping("/care/interest")//"/interest 가 맞나.."
    @ApiOperation(value = "관심 목록")
    public Object interestList(@RequestParam(required = true) final String uid,
        @RequestParam(required = true) final String desertionNo) {

        ResponseEntity response = null;
        List<Interest> interestlist = null;
        //findByUid 로 수정
        interestlist = interestDao.findByUidAndDesertionno(uid, desertionNo);

        final BasicResponse result = new BasicResponse();


        //관심목록이 없다면 관심 fail이 아니라 없음을 해야하는데..
        if(interestlist!=null) {
            result.status = true;
            result.data = "success";
            result.object = interestlist;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "success";
            result.object = null;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        
        return response;
    }


    // @PostMapping("/care/interest")
    // @ApiOperation(value = "관심목록")
    // public Object interest(@RequestBody  entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    

}