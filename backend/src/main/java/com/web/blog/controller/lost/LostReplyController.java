package com.web.blog.controller.lost;

import java.util.Optional;

import com.web.blog.dao.lost.LostreplyDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.lost.Lostreply;
import com.web.blog.model.lost.LostreplyRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class LostReplyController {

    @Autowired
    LostreplyDao lostreplyDao;

    @PostMapping("lost/reply/add")
    @ApiOperation(value = "실종/보호/목격 댓글 등록/수정")
    public Object lostReplyAdd(@RequestBody LostreplyRequest request) {

        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();

        try {

            Lostreply reply = new Lostreply();
            if(request.getLostreplyno() != null && request.getLostreplyno() != 0) {
                reply.setLostreplyno(request.getLostreplyno());
            }
            reply.setLostreplycontent(request.getLostcontent());
            reply.setLostno(request.getLostno());
            reply.setUid(request.getUid());

            lostreplyDao.save(reply);

            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @DeleteMapping("lost/reply/delete")
    @ApiOperation(value = "실종/보호/목격 댓글 삭제")
    public Object lostReplyDelete(@RequestParam final int lostreplyno, @RequestParam final String uid) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Optional<Lostreply> reply = lostreplyDao.findByLostreplynoAndUid(lostreplyno, uid);
            if(reply.isPresent()) {
                lostreplyDao.deleteByLostreplynoAndUid(lostreplyno, uid);
                result.data = "success";
            } else {
                result.data = "reply not exist";
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
    
}