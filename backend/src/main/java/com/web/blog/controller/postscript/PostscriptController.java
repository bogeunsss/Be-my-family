package com.web.blog.controller.postscript;

import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.manager.ManagerDao;
import com.web.blog.dao.postscript.PostscriptDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.manager.Manager;
import com.web.blog.model.manager.ManagerSignupRequest;
import com.web.blog.model.postscript.Postscript;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.security.JwtAuthenticationResult;
import com.web.blog.security.JwtTokenProvider;
import com.web.blog.service.ManagerMailService;
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
public class PostscriptController {
    @Autowired
    PostscriptDao postscriptdDao;

    @Autowired
    UserDao userDao;

    @PostMapping("/postscript/postAdd")
    @ApiOperation(value = "입양후기 등록")
    public Object postAdd(@RequestBody Postscript request) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Postscript postscript = request;
            String uid = request.getUid();
            postscript.setUid(uid);
            postscriptdDao.save(postscript);
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }


    // 조회는 뭐로 하나????
    
    @GetMapping("/postscript/postList")
    @ApiOperation(value = "설문조사 조회")
    public Object postList(@RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Optional<Survey> mySurvey = surveyDao.findByUid(uid);
            if(mySurvey.isPresent()) {
                result.status = true;
                result.data = "success";
                result.object = mySurvey;
            } else {
                result.status = true;
                result.data = "uid not exist";
            }
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}