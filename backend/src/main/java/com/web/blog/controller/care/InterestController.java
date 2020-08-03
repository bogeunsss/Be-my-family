package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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


    @GetMapping("/care/interestList")//"/interest 가 맞나.."
    @ApiOperation(value = "관심 목록")
    public Object interestList(@RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        List<Interest> interestlist = null;
        //findByUid 로 수정

        final BasicResponse result = new BasicResponse();
        interestlist = interestDao.findByUid(uid);
        

        //관심목록이 없다면 관심 fail이 아니라 없음을 해야하는데..
        if(interestlist!=null) {
            result.status = true;
            result.data = "success";
            result.object = interestlist;
            result.interest = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "no search";
            result.object = null;
            result.interest = false;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        
        return response;
    }

    @PostMapping("/care/interestAdd")
    @ApiOperation(value = "관심 목록 추가")
    public Object interestAdd(@Valid @RequestBody Interest request) {


        ResponseEntity response = null;

        String uid = request.getUid();
        String desertionno = request.getDesertionno();

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
    public Object interestDelete(String uid, String desertionNo) {


        ResponseEntity response = null;
        //findByUid 로 수정

        interestDao.deleteByUidAndDesertionno(uid, desertionNo);

        final BasicResponse result = new BasicResponse();

        result.status = true;
        result.data = "success";
        result.interest = true;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }


    
}