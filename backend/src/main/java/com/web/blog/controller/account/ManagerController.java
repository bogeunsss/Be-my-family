package com.web.blog.controller.account;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.adoption.AdoptionDao;
import com.web.blog.dao.manager.ManagerDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.ManagerResponse;
import com.web.blog.model.adoption.Adoption;
import com.web.blog.model.adoption.ApplicationRequest;
import com.web.blog.model.manager.Manager;
import com.web.blog.model.manager.ManagerSignupRequest;
import com.web.blog.security.JwtAuthenticationResult;
import com.web.blog.security.JwtTokenProvider;
import com.web.blog.service.ManagerMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class ManagerController {
    @Autowired
    ManagerDao managerDao;

    @Autowired
    AdoptionDao adoptionDao;

    @Autowired
    ManagerMailService managerMailService;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/manager/login")
    @ApiOperation(value = "관리자 로그인")
    public Object managerLogin(@RequestParam(required = true) final String mid,
            @RequestParam(required = true) final String password) {

        ResponseEntity response = null;
        Optional<Manager> managerOpt = managerDao.findByMidAndPassword(mid, password);
        final ManagerResponse result = new ManagerResponse();
        if (managerOpt.isPresent()) {
            result.status = true;
            result.data = "success";

            Manager manager = new Manager();
            manager.setMid(managerOpt.get().getMid());
            manager.setEmail(managerOpt.get().getEmail());

            String jwt = tokenProvider.managerToken(manager);
            result.object = new JwtAuthenticationResult(jwt);
            result.email = manager.getEmail();
            result.mid = manager.getMid();

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/manager/find")
    @ApiOperation(value = "매니저 조회")
    public Object find (@RequestParam(required = true) final String mid) {
        ResponseEntity response = null;

        Manager checkmanager = managerDao.getManagerByMid(mid);
        System.out.println(checkmanager);
        final ManagerResponse result = new ManagerResponse();
        
        try {
            result.status = true;
            result.data = "success";
            result.mid = checkmanager.getMid();
            result.name = checkmanager.getName();
            result.email = checkmanager.getEmail();
            result.phone = checkmanager.getPhone();
            result.password = checkmanager.getPassword();
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PutMapping("/manager/modify")
    @ApiOperation(value = "매니저 정보 수정")
    public Object modify(@Valid @RequestBody ManagerSignupRequest request) {
        
        ResponseEntity response = null;

        Manager checkmanager = managerDao.getManagerByMid(request.getMid());
        final ManagerResponse result = new ManagerResponse();
        
        try {
            checkmanager.setEmail(request.getEmail());
            checkmanager.setPassword(request.getPassword());
            checkmanager.setPhone(request.getPhone());
            managerDao.save(checkmanager);
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }


    @GetMapping("/manager/adoptionList")
    @ApiOperation(value = "입양신청목록 조회")
    public Object adoptionList(@RequestParam(required = true) final String mid) {
        ResponseEntity response = null;

        final ManagerResponse result = new ManagerResponse();
        
        try {
            List<Adoption> adoptionList = adoptionDao.findByMid(mid);
            result.status = true;
            result.data = "success";
            result.mid = mid;
            result.adoptions = adoptionList;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PatchMapping("/manager/adoptionList/reject")
    @ApiOperation(value = "입양신청 거절")
    public Object adoptionReject(@RequestParam(required = true) final int adoptionno) {
       
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Adoption checkadoption = adoptionDao.getByAdoptionno(adoptionno);
            checkadoption.setState(2);
            adoptionDao.save(checkadoption);
            result.data = "success";
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        
        return response;
    }

    @PatchMapping("/manager/adoptionList/approve")
    @ApiOperation(value = "입양신청 승인")
    public Object adoptionApprove(@RequestParam(required = true) final int adoptionno) {
       
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Adoption checkadoption = adoptionDao.getByAdoptionno(adoptionno);
            checkadoption.setState(1);
            adoptionDao.save(checkadoption);
            result.data = "success";
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        
        return response;
    }

    //todo
    @GetMapping("/manager/applicantInfo")
    @ApiOperation(value = "입양 신청자 정보 확인")
    public Object applicantInfo(@RequestParam Integer adoptionno) {
        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        
        try{
            Optional<Adoption> adoptionOpt = adoptionDao.findByAdoptionno(adoptionno);
            System.out.println(adoptionOpt);
            result.object = adoptionOpt;
            result.data = "success";
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        
        
        return response;
    }

}