package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.care.AdoptionDao;
import com.web.blog.dao.care.CaredetailDao;
import com.web.blog.dao.care.InterestDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.adoption.Adoption;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;
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
public class CaredetailController {
    @Autowired
    CaredetailDao caredetailDao;

    
    @Autowired
    InterestDao interestDao;

    @Autowired
    AdoptionDao adoptionDao;

    @GetMapping("/care/detail")
    @ApiOperation(value = "유기견 상세 검색")
    public Object careboarddetail(@RequestParam(required = true) final String desertionno, 
            @RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        Optional<Careboard> caredetailOpt = caredetailDao.findByDesertionno(desertionno);
        Optional<Interest> careinterestOpt = interestDao.findByUidAndDesertionno(uid, desertionno);
        Optional<Adoption> adoptionOpt = adoptionDao.findByUidAndDesertionno(uid, desertionno);
       
        final BasicResponse result = new BasicResponse();

        if(caredetailOpt!=null) {
            result.status = true;
            result.data = "success";
            result.object = caredetailOpt.get();
            System.out.println(caredetailOpt.get());
            Careboard careboard = new Careboard();
            if(careinterestOpt != null) {
                result.interest = false;
            } else {
                result.interest = true;
            }
            if(adoptionOpt != null) {
                result.adoption = false;
            } else {
                result.adoption = true;
            }
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }
}