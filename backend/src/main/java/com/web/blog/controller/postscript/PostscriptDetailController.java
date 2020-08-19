package com.web.blog.controller.postscript;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.postscript.CommentDao;
import com.web.blog.dao.postscript.PostpicDao;
import com.web.blog.dao.postscript.PostscriptDao;
import com.web.blog.dao.postscript.PostscriptgoodDao;
import com.web.blog.dao.postscript.PostscriptSearchDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.PostscriptResponse;
import com.web.blog.model.postscript.Comment;
import com.web.blog.model.postscript.Postpic;
import com.web.blog.model.postscript.Postscript;
import com.web.blog.model.postscript.Postscriptgood;
import com.web.blog.model.postscript.PostscriptRequest;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
public class PostscriptDetailController {

    @Autowired
    PostscriptDao postscriptDao;

    @Autowired
    PostscriptgoodDao postscriptgoodDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    PostpicDao postpicDao;

    @GetMapping("/postscript/detail")
    @ApiOperation(value = "입양후기 상세페이지")
    public Object postscriptDetail(@RequestParam(required = true) final Integer postscriptno,
            @RequestParam(required = false) final String uid) {

        ResponseEntity response = null;
        final PostscriptResponse result = new PostscriptResponse();

        try {
            Postscript postscript = new Postscript();
            postscript = postscriptDao.getPostscriptByPostscriptno(postscriptno);

            if (postscript != null) {

                Optional<Postscriptgood> checkGood = postscriptgoodDao.findByUidAndPostscriptno(uid, postscriptno);
                Integer goodcount = postscriptgoodDao.countByPostscriptno(postscriptno);

                if (checkGood.isPresent()) {
                    result.isGood = true;
                } else {
                    result.isGood = false;
                }

                result.object = postscript;
                result.data = "success";
                result.status = true;
                result.good = goodcount;

                List<Comment> comment = commentDao.findByPostscriptnoOrderByPostscriptno(postscriptno);

                if (!comment.isEmpty()) {
                    result.comments = comment;
                } else {
                    result.data = "comment is empty";
                }
                
                List<Postpic> postpics = postpicDao.findByPostscriptno(postscriptno);
                if(!postpics.isEmpty()) {
                    result.postpic = postpics;
                } else {
                    result.postpic = null;
                }

                response = new ResponseEntity<>(result, HttpStatus.OK);

            } else {
                result.data = "not found";
                result.status = false;
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            result.data = "exception";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        }
        return response;
    }

    @PostMapping("/postscript/good/add")
    @ApiOperation(value = "입양후기 좋아요 등록/취소")
    public Object postscriptGood(@RequestParam(required = true) final String uid,
            @RequestParam(required = true) final int postscriptno) {

        ResponseEntity response = null;
        PostscriptResponse result = new PostscriptResponse();

        try {

            Optional<Postscriptgood> good = postscriptgoodDao.findByUidAndPostscriptno(uid, postscriptno);

            if (good.isPresent()) {
                postscriptgoodDao.delete(good.get());
                result.data = "delete success";
                result.isGood = false;
            } else {
                Postscriptgood newGood = new Postscriptgood();
                newGood.setPostscriptno(postscriptno);
                newGood.setUid(uid);
                postscriptgoodDao.save(newGood);
                result.data = "insert success";
                result.isGood = true;
            }

            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.status = false;
            result.data = "fail";
        }

        return response;
    }

}