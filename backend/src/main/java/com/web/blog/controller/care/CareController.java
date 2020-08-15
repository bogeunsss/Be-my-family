package com.web.blog.controller.care;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.blog.dao.care.CareDao;
import com.web.blog.dao.care.SurveyDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.care.CareRecommend;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;
import com.web.blog.model.user.User;
import com.web.blog.service.Fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class CareController {

    @Autowired
    CareDao careDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SurveyDao surveyDao;

    @GetMapping("/care/search")
    @ApiOperation(value = "보호소 유기견 검색")
    public Object careboardSearch(@RequestParam(required = true) final String category,
            @RequestParam(required = true) final String searchText, @RequestParam(required = true) int pageno) {

        ResponseEntity response = null;
        Page<Careboard> careOpt = null;

        if (category.equals("careAddr")) {
            careOpt = careDao.findByCareaddrContaining(searchText,
                    PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));
        } else if (category.equals("kindCd")) {
            careOpt = careDao.findBykindcdContaining(searchText,
                    PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));
        }

        final BasicResponse result = new BasicResponse();

        if (careOpt != null) {
            result.status = true;
            result.data = "success";
            result.object = careOpt;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/care/list")
    @ApiOperation(value = "보호소 유기견 조회")
    public Object careboardList(@RequestParam(required = true) final int pageno,
            @RequestParam(required = false) String uid) {

        ResponseEntity response = null;
        Page<Careboard> careList = null;
        // Page<CareRecommend> careRecommendList = null;
        List<CareRecommend> careRecommends = new ArrayList<>();
        final BasicResponse result = new BasicResponse();
        // 리스트랑 설문조사 객체 보내주기
        try {
            if (uid == null) {
                uid = "";
            }
            Optional<User> user = userDao.findByUid(uid);
            Optional<Survey> survey = surveyDao.findByUid(uid);
            if (user.isPresent() && survey.isPresent() && user.get().getFlag() == 1) {
                careList = careDao.findAll(PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));
                System.out.println(careList.getContent().size());
                for (int i = 0; i < careList.getContent().size(); i++) {
                    CareRecommend careRecommend = new CareRecommend();
                    careRecommend.setDesertionno(careList.getContent().get(i).getDesertionno());
                    careRecommend.setNoticeedt(careList.getContent().get(i).getNoticeedt());
                    careRecommend.setPopfile(careList.getContent().get(i).getPopfile());
                    careRecommend.setSexcd(careList.getContent().get(i).getSexcd());
                    careRecommend.setNeuteryn(careList.getContent().get(i).getNeuteryn());
                    careRecommend.setSpecialmark(careList.getContent().get(i).getSpecialmark());
                    careRecommend.setCarenm(careList.getContent().get(i).getCarenm());
                    careRecommend.setCareaddr(careList.getContent().get(i).getCareaddr());
                    careRecommend.setOrgnm(careList.getContent().get(i).getOrgnm());
                    careRecommend.setChargenm(careList.getContent().get(i).getChargenm());
                    careRecommend.setOfficetel(careList.getContent().get(i).getOfficetel());
                    careRecommend.setNoticecomment(careList.getContent().get(i).getNoticecomment());
                    careRecommend.setNumofrows(careList.getContent().get(i).getNumofrows());
                    careRecommend.setPageno(careList.getContent().get(i).getPageno());
                    careRecommend.setTotalcount(careList.getContent().get(i).getTotalcount());
                    careRecommend.setResultcode(careList.getContent().get(i).getResultcode());
                    careRecommend.setResultmsg(careList.getContent().get(i).getResultmsg());
                    careRecommend.setFilename(careList.getContent().get(i).getFilename());
                    careRecommend.setHappendt(careList.getContent().get(i).getHappendt());
                    careRecommend.setHappenplace(careList.getContent().get(i).getHappenplace());
                    careRecommend.setKindcd(careList.getContent().get(i).getKindcd());
                    careRecommend.setColorcd(careList.getContent().get(i).getColorcd());
                    careRecommend.setAge(careList.getContent().get(i).getAge());
                    careRecommend.setWeight(careList.getContent().get(i).getWeight());
                    careRecommend.setNoticeno(careList.getContent().get(i).getNoticeno());
                    careRecommend.setNoticesdt(careList.getContent().get(i).getNoticesdt());
                    careRecommend.setRecommend(true);
                    boolean checkReco = Fit.recommend(careRecommend, survey);
                    careRecommend.setRecommend(checkReco);
                    careRecommends.add(careRecommend);
                }

                // careRecommendList = careList.get();
                result.object = careRecommends;
                result.data = "recommend List";
                result.status = true;
                response = new ResponseEntity<>(result, HttpStatus.OK);

            } else {
                careList = careDao.findAll(PageRequest.of(pageno, 12, Sort.Direction.DESC, "Noticesdt"));

                if (careList != null) {
                    result.status = true;
                    result.data = "not user or no survey";
                    result.object = careList;
                    response = new ResponseEntity<>(result, HttpStatus.OK);
                } else {
                    result.status = true;
                    result.data = "no data";
                    response = new ResponseEntity<>(result, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

}
