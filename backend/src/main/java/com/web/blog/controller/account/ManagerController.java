package com.web.blog.controller.account;

import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.manager.ManagerDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.manager.Manager;
import com.web.blog.model.manager.ManagerSignupRequest;
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
public class ManagerController {
    @Autowired
    ManagerDao managerDao;

    @Autowired
    ManagerMailService managerMailService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/manager/login")
    @ApiOperation(value = "관리자 로그인")
    public Object managerLogin(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) {

        ResponseEntity response = null;
        Optional<Manager> managerOpt = managerDao.findManagerByEmailAndPassword(email, password);

        final BasicResponse result = new BasicResponse();
        if (managerOpt.isPresent()) {
            result.status = true;
            result.data = "success";

            Manager manager = new Manager();
            manager.setMid(managerOpt.get().getMid());
            manager.setEmail(email);
            manager.setPassword(password);

            String jwt = tokenProvider.managerToken(manager);
            result.object = new JwtAuthenticationResult(jwt);
            result.email = manager.getEmail();
            result.mid = manager.getMid();
            result.password = manager.getPassword();

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PostMapping("/manager/signup")
    @ApiOperation(value = "관리자 가입하기")
    public Object managerSignup(@Valid @RequestBody ManagerSignupRequest request) {

        String mid = request.getMid();
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();
        String phone = request.getPhone();
        String carenm = request.getCareNM();

        Manager emailCheck = managerDao.getManagerByEmail(email);
        Manager midcheck = managerDao.getManagerByMid(mid);

        final BasicResponse result = new BasicResponse();

        if (emailCheck != null) {
            result.status = true;
            result.data = "emailexist";
        } else if (midcheck != null) {
            result.status = true;
            result.data = "nicknameexist";
        } else {
            result.status = true;
            result.data = "success";
            Manager manager = new Manager();
            manager.setMid(mid);
            manager.setEmail(email);
            manager.setPassword(password);
            manager.setName(name);
            manager.setPhone(phone);
            manager.setCareNM(carenm);
            managerDao.save(manager);

            if (managerMailService.managerMailSend(manager)) {
                result.data = "emailsuccess";
            } else {
                result.data = "emailfail";
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/manager/update")
    @ApiOperation(value = "관리자 수정하기")
    public Object managerModify(@Valid @RequestBody ManagerSignupRequest request) {

        Manager checkManager = managerDao.getManagerByMid(request.getMid());
        ResponseEntity response = null;

        checkManager.setEmail(request.getEmail());
        checkManager.setPassword(request.getPassword());
        checkManager.setName(request.getName());
        checkManager.setPhone(request.getPhone());
        managerDao.save(checkManager);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = checkManager;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @DeleteMapping("/manager/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(String mid) {

        ResponseEntity response = null;
        managerDao.deleteById(mid);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

}