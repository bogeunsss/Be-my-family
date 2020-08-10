package com.web.blog.controller.account;

import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
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
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    MailService mailService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) {

        ResponseEntity response = null;
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
        System.out.println("======");
        System.out.println(userOpt);
        System.out.println("======");
        final BasicResponse result = new BasicResponse();
        if (userOpt.isPresent()) {
            result.status = true;
            result.data = "success";

            User user = new User();
            user.setUid(userOpt.get().getUid());
            user.setEmail(email);
            user.setPassword(password);

            String jwt = tokenProvider.generateToken(user);
            result.object = new JwtAuthenticationResult(jwt);
            result.email = user.getEmail();
            result.uid = user.getUid();
            result.password = user.getPassword();

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")

    public Object signup(@Valid @RequestBody SignupRequest request) {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.

        String nickName = request.getUid();
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        String phone = request.getPhone();
        String job = request.getJob();
        int marriaged = request.getMarriaged();
        int sex = request.getSex();
        String birthdate = request.getBirthdate();

        User emailCheck = userDao.getUserByEmail(email);
        User nickNameCheck = userDao.getUserByUid(nickName);

        final BasicResponse result = new BasicResponse();

        if (emailCheck != null) {
            result.status = true;
            result.data = "emailexist";
        } else if (nickNameCheck != null) {
            result.status = true;
            result.data = "nicknameexist";
        } else {
            result.status = true;
            result.data = "success";
            User user = new User();
            user.setUid(nickName);
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setPhone(phone);
            user.setJob(job);
            user.setMarriaged(marriaged);
            user.setSex(sex);
            user.setBirthdate(birthdate);
            userDao.save(user);

            if (mailService.mailSend(user)) {
                result.data = "emailsuccess";
            } else {
                result.data = "emailfail";
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object modify(@Valid @RequestBody SignupRequest request) {
        // userid로 확인
        User checkUser = userDao.getUserByUid(request.getUid());
        ResponseEntity response = null;

        checkUser.setEmail(request.getEmail());
        checkUser.setPassword(request.getPassword());
        userDao.save(checkUser);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    // 조회
    @GetMapping("/account/find")
    @ApiOperation(value = "조회하기")
    public Object find(String email) {

        // userid로 확인
        // String findUserid = request.getUid();
        ResponseEntity response = null;

        User newUser = userDao.getUserByEmail(email);
        
        final BasicResponse result = new BasicResponse();
        
        result.status = true;
        result.data = "success";
        result.object = newUser;
        result.name = newUser.getName();
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    // 삭제
    @DeleteMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(String uid) {

        // userid로 확인
        ResponseEntity response = null;
        userDao.deleteById(uid);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

}