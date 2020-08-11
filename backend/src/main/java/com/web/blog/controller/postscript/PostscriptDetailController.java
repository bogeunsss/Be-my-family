package com.web.blog.controller.postscript;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.postscript.CommentDao;
import com.web.blog.dao.postscript.PostscriptDao;
import com.web.blog.dao.postscript.PostscriptgoodDao;
import com.web.blog.dao.postscript.PostscriptSearchDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.PostscriptResponse;
import com.web.blog.model.postscript.Comment;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;




@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
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

    @GetMapping("/postscript/detail")
    @ApiOperation(value = "입양후기 상세페이지")
    public Object postscriptDetail(@RequestParam(required = true) final Integer postscriptno) {

        ResponseEntity response = null;
        final PostscriptResponse result = new PostscriptResponse();

        
        try {
            Postscript postscript = new Postscript();
            postscript = postscriptDao.getPostscriptByPostscriptno(postscriptno);
            
            if(postscript != null) {
                
                Integer goodcount = postscriptgoodDao.countByPostscriptno(postscriptno);
                result.object = postscript;
                result.data = "success";
                result.status = true;
                result.good = goodcount;

                List<Comment> comment = commentDao.findByPostscriptnoOrderByPostscriptno(postscriptno);
                if(!comment.isEmpty()) {
                    result.comments = comment;
                } else {
                    result.data = "comment is empty";
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

}