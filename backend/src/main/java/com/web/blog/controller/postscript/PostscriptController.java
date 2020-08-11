package com.web.blog.controller.postscript;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.blog.dao.postscript.CommentDao;
import com.web.blog.dao.postscript.PostscriptDao;
import com.web.blog.dao.postscript.PostscriptSearchDao;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.postscript.Postscript;
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
public class PostscriptController {
    
    @Autowired
    PostscriptDao postscriptDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PostscriptSearchDao postscriptSearchDao;

    @Autowired
    CommentDao commentDao;

    @GetMapping("/postscript/List")
    @ApiOperation(value = "입양후기 리스트")
    public Object postscriptList() {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        List<Postscript> postscriptList = postscriptDao.findAll();

        try {
            if (!postscriptList.isEmpty()) {
                result.object = postscriptList;
                result.status = true;
                result.data = "success";

                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.status = false;
                result.data = "no List";
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } catch (final Exception e) {

            e.printStackTrace();

            result.status = false;
            result.data = "fail";

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/postscript/Add")
    @ApiOperation(value = "입양후기 등록")
    public Object postscriptAdd(@RequestBody PostscriptRequest request) {

        ResponseEntity response = null;

        String checkuid = request.getUid();
        String checktitle = request.getTitle();
        String checkcontent = request.getContent();
        String checkimage = request.getImage();
        String checksido = request.getSido();
        String checkgugun = request.getGugun();
        String checkkind = request.getKind();

        final BasicResponse result = new BasicResponse();

        try {

            Postscript postscript = new Postscript();
            postscript.setUid(checkuid);
            postscript.setTitle(checktitle);
            postscript.setContent(checkcontent);
            postscript.setImage(checkimage);
            postscript.setSido(checksido);
            postscript.setGugun(checkgugun);
            postscript.setKind(checkkind);
            postscriptDao.save(postscript);

            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {

            result.status = false;
            result.data = "fail";

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @DeleteMapping("/postscript/Delete")
    @ApiOperation(value = "입양후기 게시글 삭제")
    public Object postscriptDelete(@RequestParam(required = true) final int postscriptno) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        Optional<Postscript> postscriptOpt = postscriptDao.findByPostscriptno(postscriptno);

        System.out.println(postscriptOpt);

        if (postscriptOpt.isPresent()) {
            postscriptDao.deleteByPostscriptno(postscriptno);
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        else {
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/postscript/Search")
    @ApiOperation(value = "입양후기 조회")
    public Object postscriptSearch(@RequestParam(required = true) final String category,
            @RequestParam(required = true) final String searchText) {

        ResponseEntity response = null;
        List<Postscript> postscriptList = null;
        final BasicResponse result = new BasicResponse();

        if (category.equals("uid")) {
            postscriptList = postscriptSearchDao.findByUidContainingOrderByPostscriptnoDesc(searchText);
        } else if (category.equals("title")) {
            postscriptList = postscriptSearchDao.findByTitleContainingOrderByPostscriptnoDesc(searchText);
        }

        try {
            if (!postscriptList.isEmpty()) {
                result.data = "success";
                result.status = true;
                result.object = postscriptList;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.data = "no search";
                result.status = false;
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            result.status = false;
            result.data = "fail";

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/postscript/Modify")
    @ApiOperation(value = "입양 후기 게시판 글 수정")
    public Object postscriptModify(@Valid @RequestBody PostscriptRequest request) {

        Postscript postscript = postscriptDao.getPostscriptByPostscriptno(request.getPostscriptno());
        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();

        try {

            postscript.setTitle(request.getTitle());
            postscript.setContent(request.getContent());
            postscript.setImage(request.getImage());
            postscript.setKind(request.getKind());
            postscriptDao.save(postscript);

            result.status = true;
            result.data = "success";

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {

            result.data = "success";
            result.status = false;

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}