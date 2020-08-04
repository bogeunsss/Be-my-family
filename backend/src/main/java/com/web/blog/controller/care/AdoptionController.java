package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.care.AdoptionDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.Adoption;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.security.JwtAuthenticationResult;
import com.web.blog.security.JwtTokenProvider;
import com.web.blog.service.MailService;

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
public class AdoptionController {
        @Autowired
        UserDao userDao;

        @Autowired
        AdoptionDao adoptionDao;


        //상담 시간, 상담 날짜 테이블 추가, 객체로 받기
        //uid 말고 이메일로 받기
    @GetMapping("/account/adoptionList")
    @ApiOperation(value = "사용자 입양 목록 리스트")
    public Object userAdoptionList(@RequestParam(required = true) final String uid) { //uid ==> email

        ResponseEntity response = null;
        List<Adoption> AdoptionList = null;

        final BasicResponse result = new BasicResponse();
        AdoptionList = adoptionDao.findByUid(uid);

        if(AdoptionList!=null) {
                result.status = true;
                result.data = "success";
                result.object = AdoptionList;
                response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
                result.data = "no search";
                result.object = null;
                response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;
    }

}