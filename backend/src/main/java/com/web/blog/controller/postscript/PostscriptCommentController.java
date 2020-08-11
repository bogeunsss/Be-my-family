package com.web.blog.controller.postscript;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.web.blog.dao.lost.LostDao;
import com.web.blog.dao.lost.LosttagDao;
import com.web.blog.dao.postscript.CommentDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.lost.Lost;
import com.web.blog.model.lost.LostPic;
import com.web.blog.model.lost.LostRequest;
import com.web.blog.model.lost.Losttag;
import com.web.blog.model.postscript.Comment;
import com.web.blog.model.postscript.CommentRequest;

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
public class PostscriptCommentController {
        
        @Autowired
        CommentDao commentDao;

        @PostMapping("/comment/add")
        @ApiOperation(value = "댓글 등록")
        public Object commentAdd(@RequestBody CommentRequest request) {

                ResponseEntity response = null;
                final BasicResponse result = new BasicResponse();

                try {
                        Comment comment = new Comment();
                        comment.setUid(request.getUid());
                        comment.setPostscriptno(request.getPostscriptno());
                        comment.setContent(request.getContent());
                        commentDao.save(comment);
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

        @PutMapping("/comment/modify")
        @ApiOperation(value = "댓글 수정")
        public Object commentModify(@RequestBody CommentRequest request) {

                ResponseEntity response = null;
                final BasicResponse result = new BasicResponse();

                try {
                        Comment comment = new Comment();
                        comment.setContent(request.getContent());
                        commentDao.save(comment);
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