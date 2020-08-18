package com.web.blog.controller.account;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;
import com.web.blog.dao.adoption.AdoptionDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.adoption.Adoption;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.security.JwtAuthenticationResult;
import com.web.blog.security.JwtTokenProvider;
import com.web.blog.service.MailService;
import com.web.blog.service.PasswordService;

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

@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    AdoptionDao adoptionDao;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordService passwordService;

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
    @ApiOperation(value = "가입요청")
    public Object signup(@Valid @RequestBody SignupRequest request) {

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

            String key = mailService.makekey(6, 1);

            if (mailService.mailSend(user, key)) {
                result.data = key;
                result.object = user;
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

        // checkUser.setEmail(request.getEmail());
        checkUser.setPassword(request.getPassword());
        checkUser.setPhone(request.getPhone());
        checkUser.setJob(request.getJob());
        checkUser.setMarriaged(request.getMarriaged());
        

        userDao.save(checkUser);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @GetMapping("/account/find")
    @ApiOperation(value = "조회하기")
    public Object find(String email) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        User newUser = userDao.getUserByEmail(email);

        List<Adoption> adoptionList = null;

        try {

            adoptionList = adoptionDao.findByUid(newUser.getUid());

            result.status = true;
            result.data = "success";
            result.object = newUser;
            result.adoptions = adoptionList;
            result.name = newUser.getName();

            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {

            result.status = false;
            result.data = "not fount user";

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        }

        return response;
    }

    @DeleteMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(String uid) {

        ResponseEntity response = null;
        userDao.deleteById(uid);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @PostMapping("/account/emailkey")
    @ApiOperation(value = "가입완료")
    public Object emailKey(@Valid @RequestBody SignupRequest request) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            String nickName = request.getUid();
            String email = request.getEmail();
            String password = request.getPassword();
            String name = request.getName();
            String phone = request.getPhone();
            String job = request.getJob();
            int marriaged = request.getMarriaged();
            int sex = request.getSex();
            String birthdate = request.getBirthdate();

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

            result.data = "success";
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;

    }

    @PostMapping("/account/findpassword")
    @ApiOperation(value = "비밀번호찾기")
    public Object findPassword(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {

            Optional<User> user = userDao.findByUidAndEmail(uid, email);

            if (user.isPresent()) {
                String password = passwordService.makePassword(10);
                user.get().setEmail(email);
                user.get().setPassword(password);
                user.get().setUid(uid);
                userDao.save(user.get());

                result.data = "success";
                if (passwordService.mailSend(user, password)) {
                    result.data = "success";
                } else {
                    result.data = "emailfail";
                }
            } else {
                result.data = "no user";
            }

            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;

    }

}