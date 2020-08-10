package com.web.blog.controller.care;

import java.util.List;
import java.util.Optional;

import com.web.blog.dao.care.SurveyDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.Survey;

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

public class SurveyController {

    @Autowired
    SurveyDao surveyDao;

    @PostMapping("/care/surveyAdd")
    @ApiOperation(value = "설문 조사 등록/수정")
    public Object surveyAdd(@RequestBody Survey request) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Survey survey = request;
            String uid = request.getUid();
            survey.setUid(uid);
            surveyDao.save(survey);
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

    @GetMapping("/care/survey")
    @ApiOperation(value = "설문조사 조회")
    public Object survey(@RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();
        System.out.println(uid);
        try {
            Optional<Survey> mySurvey = surveyDao.findByUid(uid);
            System.out.println(mySurvey);
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