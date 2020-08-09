package com.web.blog.controller.lost;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.web.blog.dao.lost.LostDao;
import com.web.blog.dao.lost.LosttagDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.lost.Lost;
import com.web.blog.model.lost.LostPic;
import com.web.blog.model.lost.LostRequest;
import com.web.blog.model.lost.Losttag;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class LostController {

    @Autowired
    LostDao lostDao;

    @Autowired
    LosttagDao losttagDao;

    @PostMapping("/lost/add")
    @ApiOperation(value = "실종/입양/보호 글 등록")
    public Object lostAdd(@RequestPart final List<MultipartFile> files, LostRequest request) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {

            Lost lost = new Lost();
            LostPic lostPics = new LostPic();

            // 사진 업로드
            int count = 0;
            lostPics.setLostPics(files);

            System.out.println("lostpics =======>" + lostPics.getLostPics());

            for (MultipartFile file : lostPics.getLostPics()) {
                final String originalfileName = file.getOriginalFilename();
                final String filepath = "C:/Image/" + originalfileName;
                final File dest = new File(filepath);
                file.transferTo(dest);

                System.out.println("filepath =======>" + filepath);

                if (count == 0)
                    lost.setLostpic1(filepath);
                else if (count == 1)
                    lost.setLostpic2(filepath);
                else if (count == 2)
                    lost.setLostpic3(filepath);
                count++;
            }

            // 요청 객체 Lost dto에 저장
            lost.setLostage(request.getLostage());
            lost.setLostbreed(request.getLostbreed());
            lost.setLostcontent(request.getLostcontent());
            lost.setLostdate(request.getLostdate());
            lost.setLostgugun(request.getLostgugun());
            lost.setLostphone(request.getLostphone());
            lost.setLostplace(request.getLostplace());
            lost.setLostsex(request.getLostsex());
            lost.setLostsido(request.getLostsido());
            lost.setLosttype(request.getLosttype());
            lost.setUid(request.getUid());

            // lost db
            lostDao.save(lost);

            // tag dto, db
            for (String tagname : request.getLosttagtext()) {
                Losttag tag = new Losttag();
                tag.setLostno(lost.getLostno());
                tag.setTagname(tagname);
                System.out.println("tag =======>" + tag);
                losttagDao.save(tag);
            }

            Losttag tagBreed = new Losttag();
            tagBreed.setLostno(lost.getLostno());
            tagBreed.setTagname(lost.getLostbreed());
            losttagDao.save(tagBreed);

            Losttag tagSido = new Losttag();
            tagSido.setLostno(lost.getLostno());
            tagSido.setTagname(lost.getLostsido());
            losttagDao.save(tagSido);

            Losttag tagGugun = new Losttag();
            tagGugun.setLostno(lost.getLostno());
            tagGugun.setTagname(lost.getLostgugun());
            losttagDao.save(tagGugun);

            // 반환
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (final Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("lost/list")
    @ApiOperation(value = "실종/보호/목격 전체 조회")
    public Object lostList() {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {

            List<Lost> lostList = lostDao.findAll();
            result.status = true;
            result.data = "success";
            result.object = lostList;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("lost/detail")
    @ApiOperation(value = "실종/목격/보호 상세 조회")
    public Object lostDetail(@RequestParam(required = true) int lostno) {
        
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try{
            Optional<Lost> lostDetail = lostDao.findByLostno(lostno);
            List<Losttag> tagList = losttagDao.findByLostno(lostno);
            
            if(lostDetail.isPresent())  {
                result.status = true;
                result.data = "success";
                result.object = lostDetail.get();
                if(!tagList.isEmpty()) {
                    result.tag = tagList;
                } 
            } else {
                result.status = true;
                result.data = "lostno not exist";
            }

            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        
        return response;
    }

    @DeleteMapping("lost/delete")
    @ApiOperation(value = "실종/목격/보호 글 삭제")
    public Object lostDelete(@RequestParam(required = true) final int lostno, @RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();

        try {
            Optional<Lost> lost = lostDao.findByLostno(lostno);
            if (lost.isPresent()) {
                if (lost.get().getUid().equals(uid)) {
                    
                    if(lost.get().getLostpic1() != null) {
                        File file = new File(lost.get().getLostpic1());
                        if(file.exists() == true){
                            file.delete();
                        }
                    }

                    if(lost.get().getLostpic2() != null) {
                        File file = new File(lost.get().getLostpic2());
                        if(file.exists() == true){
                            file.delete();
                        }
                    }

                    if(lost.get().getLostpic3() != null) {
                        File file = new File(lost.get().getLostpic3());
                        if(file.exists() == true){
                            file.delete();
                        }
                    }

                    losttagDao.deleteByLostno(lostno);
                    lostDao.deleteByLostno(lostno);
                    result.data = "success";

                } else {
                    result.data = "uid diff";
                }
            } else {
                result.data = "lostno not exsit";
            }

            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return response;

    }

    @PutMapping("lost/update")
    @ApiOperation(value = "실종/목격/보호 글 수정")
    public Object lostUpdate(LostRequest request, @RequestParam(required = true) final String uid) {

        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();

        try {

        } catch(Exception e) {

        }

        return response;
    }
    

}