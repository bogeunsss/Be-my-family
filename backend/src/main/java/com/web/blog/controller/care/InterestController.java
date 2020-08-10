package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;


import com.web.blog.dao.care.InterestDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/care/interestList")
    @ApiOperation(value = "관심 목록 리스트")
    public Object interestList(@RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        List<Interest> interestlist = null;

        final BasicResponse result = new BasicResponse();
        interestlist = interestDao.findByUid(uid);

        if(!interestlist.isEmpty()) {
            result.status = true;
            result.data = "success";
            result.object = interestlist;
            result.interest = true;
            result.uid = uid;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "no search";
            result.object = null;
            result.interest = false;
            result.uid = uid;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        
        return response;
    }

    @PostMapping("/care/interestAdd")
    @ApiOperation(value = "관심 목록 추가")
    public Object interestAdd(@RequestParam(required = true) final String uid,
        @RequestParam(required = true) final String desertionno) {

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();

        Interest interest = new Interest();
        interest.setUid(uid);
        interest.setDesertionno(desertionno);
        interestDao.save(interest);

        result.status = true;
        result.data = "success";
        result.interest = true;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @DeleteMapping("/care/interestDelete")
    @ApiOperation(value = "관심 목록 삭제")
    public Object interestDelete(@RequestParam String uid, @RequestParam String desertionno) {
        ResponseEntity response = null;

        try{
            Optional<Interest> interest = interestDao.findByUidAndDesertionno(uid, desertionno);
            System.out.println(interest);

            final BasicResponse result = new BasicResponse();

            if(interest.isPresent()){
                interestDao.deleteByInterestno(interest.get().getInterestno());
                result.status = true;
                result.data = "success";
                result.interest = true;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        }   catch(Exception e) {
            System.out.println(e.getMessage());
            final BasicResponse result = new BasicResponse();
            result.status = false;
            result.data = "fail";
            result.interest = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }  
        
        return response;
    }
}