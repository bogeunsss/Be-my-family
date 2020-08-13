package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.adoption.AdoptionDao;
import com.web.blog.dao.care.CareDao;
import com.web.blog.dao.care.SurveyDao;
import com.web.blog.dao.manager.ManagerDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.AdoptionResponse;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.adoption.Adoption;
import com.web.blog.model.adoption.ApplicationRequest;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;
import com.web.blog.model.manager.Manager;
import com.web.blog.model.user.User;
import com.web.blog.service.MailService;
import com.web.blog.service.ManagerMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AdoptionController {
        @Autowired
        UserDao userDao;

        @Autowired
        AdoptionDao adoptionDao;

        @Autowired
        SurveyDao surveydao;

        @Autowired
        MailService mailService;

        @Autowired
        ManagerDao managerDao;

        @Autowired
        ManagerMailService managerMailService;


        @Autowired
        CareDao careDao;


            //입양 신청
    @PostMapping("/adoption/Application")
    @ApiOperation(value = "입양신청")
    public Object adoptionLAplication(@Valid @RequestParam(required = true) final String email,
        @RequestParam(required = true) final String desertionno) { 

        ResponseEntity response = null;
        
        final AdoptionResponse result = new AdoptionResponse();
        try{
            Optional<User> userOpt = userDao.findByEmail(email);
            if(userOpt.isPresent()) {
                Optional<Survey> surveyOpt = surveydao.findByUid(userOpt.get().getUid());
                if(surveyOpt.isPresent()) {
                    Optional<Adoption> adoptionOpt = adoptionDao.findByUidAndDesertionno(userOpt.get().getUid(), desertionno);
                    if(!adoptionOpt.isPresent()) {
                        Careboard careboard = careDao.findByDesertionno(desertionno);
                        String checkmid = careboard.getCarenm();
                        System.out.println("success");
                        result.status = true;
                        result.data = "success";
                        result.checksurvey = true;
                        result.checkadoption = true;
                        result.survey = surveyOpt;
                        result.user = userOpt;
                        result.mid = checkmid;
                        result.desertionno = desertionno;
                        response = new ResponseEntity<>(result, HttpStatus.OK);
                    } else {
                        System.out.println("Already apllication");
                        result.status = false;
                        result.data = "Already apllication";
                        result.checksurvey = true;
                        result.checkadoption = false;
                        result.user = userOpt;
                        response = new ResponseEntity<>(result, HttpStatus.OK);
                    }
                }else {
                    System.out.println("It hasn't been surveyed yet.");
                    result.status = false;
                    result.data = "It hasn't been surveyed yet.";
                    result.checksurvey = false;
                    result.user = userOpt;
                    response = new ResponseEntity<>(result, HttpStatus.OK);
                }
            }else {
                System.out.println("not user");
                result.status = false;
                result.data = "not user";
                result.user = null;
                response = new ResponseEntity<>(result, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
        } catch(Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        
            return response;
    }

        
    //입양 신청
    @PostMapping("/adoption/Success")
    @ApiOperation(value = "입양신청완료")
        public Object adoptionLSuccess(@Valid @RequestBody ApplicationRequest request) { 
                
        ResponseEntity response = null;
        
        Adoption adoption = new Adoption();
        adoption.setUid(request.getUid());
        adoption.setName(request.getName());
        // 이메일 주석처리 되어있었는데 한번 확인 필요
        adoption.setEmail(request.getEmail());
        adoption.setPhone(request.getPhone());
        adoption.setJob(request.getJob());
        adoption.setMarriaged(request.getMarriaged());
        adoption.setSex(request.getSex());

        adoption.setMid(request.getMid());

        adoption.setNation(request.getNation());
        adoption.setSido(request.getSido());
        adoption.setGugun(request.getGugun());
        adoption.setPlace(request.getPlace());
        adoption.setBeforeover(request.isBeforeover());
        adoption.setBeforeadopt(request.isBeforeadopt());
        adoption.setPresentanimal(request.getPresentanimal());
        adoption.setFamilyunder(request.getFamilyunder());
        adoption.setFamilymiddle(request.getFamilymiddle());
        adoption.setFamilyagree(request.isFamilyagree());
        adoption.setDissolution(request.getDissolution());
        adoption.setSickness(request.isSickness());
        adoption.setAlone(request.getAlone());
        adoption.setTemp(request.getTemp());
        adoption.setHouse(request.getHouse());
        adoption.setEatmoney(request.getEatmoney());
        adoption.setCaremoney(request.getCaremoney());
        adoption.setReason(request.getReason());
        adoption.setThink(request.getThink());

        adoption.setDesertionno(request.getDesertionno());

        adoption.setFixdate(request.getFixdate());
        adoption.setFixtime(request.getFixtime());
        adoptionDao.save(adoption);

        BasicResponse result = new BasicResponse();

        // if(managerMailService.managerMailSend(manager)) {
        //     result.data = "send email success";
        // } else {
        //     result.data = "send email fail";
        // }

        result.status = true;

        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/adoption/delete")
    @ApiOperation(value = "입양 신청 삭제")
    public Object adoptionDelete(String uid, String desertionno) {
        ResponseEntity response = null;
        
        final BasicResponse result = new BasicResponse();
        try {
            Optional<Adoption> adoption = adoptionDao.findByUidAndDesertionno(uid, desertionno);
            
            if(adoption.isPresent()) {
                adoptionDao.deleteByUidAndDesertionno(uid, desertionno);
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.data = "not found";
                result.status = false;
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        }   catch(Exception e) {
            System.out.println(e.getMessage());
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }



}

