package com.web.blog.controller.postscript;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.web.blog.dao.postscript.CommentDao;
import com.web.blog.dao.postscript.PostpicDao;
import com.web.blog.dao.postscript.PostscriptDao;
import com.web.blog.dao.postscript.PostscriptPageDao;
import com.web.blog.dao.postscript.PostscriptSearchDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.PostscriptResponse;
import com.web.blog.model.postscript.Postpic;

import com.web.blog.model.postscript.Postscript;
import com.web.blog.model.postscript.PostscriptRequest;
import com.web.blog.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class PostscriptController {

    @Autowired
    PostscriptDao postscriptDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PostscriptSearchDao postscriptSearchDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    PostpicDao postpicDao;

    @Autowired
    PostscriptPageDao postscriptpageDao;

    @GetMapping("/postscript/List")
    @ApiOperation(value = "입양후기 게시글 리스트")
    public Object postscriptList(@RequestParam int pageno) {

        ResponseEntity response = null;
        final PostscriptResponse result = new PostscriptResponse();

        try {
            Page<Postscript> post = postscriptpageDao
                    .findAll(PageRequest.of(pageno - 1, 15, Sort.Direction.DESC, "postscriptno"));

            List<Postscript> postList = post.getContent();
            int totalPage = post.getTotalPages();
            boolean hasNext = post.hasNext();
            long totalData = post.getTotalElements();
            boolean isData = post.hasContent();
            int currentPage = post.getNumber();
            int currentData = post.getNumberOfElements();

            result.object = postList;
            result.totalPage = totalPage;
            result.hasNext = hasNext;
            result.totalData = totalData;
            result.currentPage = currentPage + 1;
            result.currentData = currentData;

            if (isData) {
                result.data = "success";
            } else {
                result.data = "no data";
            }
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("/postscript/Add")
    @ApiOperation(value = "입양후기 게시글 등록")
    public Object postscriptAdd(@RequestPart(required = false) final List<MultipartFile> images,
            PostscriptRequest request) {

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

            List<Postpic> postpicList = new ArrayList<>();
            for (MultipartFile file : images) {
                final String originalfileName = file.getOriginalFilename();
                final String filepath = "C:/Image/" + originalfileName;
                final File dest = new File(filepath);
                file.transferTo(dest);
                Postpic postpic = new Postpic();
                postpic.setPostscriptno(postscript.getPostscriptno());
                postpic.setPostpath(filepath);
                postpicList.add(postpic);
            }
            postpicDao.saveAll(postpicList);

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
    public Object postscriptDelete(@RequestParam(required = true) final int postscriptno,
    @RequestParam(required = true) final String Uid) {

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            Optional<Postscript> postscriptOpt = postscriptDao.findByPostscriptnoAndUid(postscriptno, Uid);
            Optional<User> userOpt = userDao.findByUid(Uid);
            System.out.println(postscriptOpt);
            if(!userOpt.isPresent()) {
                result.data = "not user";
                result.status = true;
            } else {
                if (!postscriptOpt.isPresent()) {
                    result.data = "Not your posting";
                    result.status = true;
                } else {
                    postscriptDao.deleteByPostscriptno(postscriptno);
                    result.data = "success";
                    result.status = true;
                }
            }
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.data = "fail";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/postscript/Search")
    @ApiOperation(value = "입양후기 게시글 검색")
    public Object postscriptSearch(@RequestParam(required = true) final String category,
            @RequestParam(required = true) final String searchText, @RequestParam(required = true) int pageno) {

        ResponseEntity response = null;
        Page<Postscript> post = null;
        final PostscriptResponse result = new PostscriptResponse();

        try {
            if (category.equals("uid")) {
                post = postscriptSearchDao.findByUidContainingOrderByPostscriptnoDesc(searchText,
                        PageRequest.of(pageno - 1, 15, Sort.Direction.DESC, "postscriptno"));
            } else if (category.equals("title")) {
                post = postscriptSearchDao.findByTitleContainingOrderByPostscriptnoDesc(searchText,
                        PageRequest.of(pageno - 1, 15, Sort.Direction.DESC, "postscriptno"));
            }

            List<Postscript> postList = post.getContent();
            int totalPage = post.getTotalPages();
            boolean hasNext = post.hasNext();
            long totalData = post.getTotalElements();
            boolean isData = post.hasContent();
            int currentPage = post.getNumber();
            int currentData = post.getNumberOfElements();

            result.object = postList;
            result.totalPage = totalPage;
            result.hasNext = hasNext;
            result.totalData = totalData;
            result.currentPage = currentPage + 1;
            result.currentData = currentData;

            if (isData) {
                result.data = "success";
            } else {
                result.data = "no data";
            }
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {

            result.status = false;
            result.data = "fail";

            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/postscript/Modify")
    @ApiOperation(value = "입양후기 게시글 수정")
    public Object postscriptModify(@RequestPart(required = false) final List<MultipartFile> images,
            PostscriptRequest request) {


        System.out.println(request.getPostscriptno());
        System.out.println(request.getContent());
        Postscript postscript = postscriptDao.getPostscriptByPostscriptno(request.getPostscriptno());
        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();

        try {

            postscript.setTitle(request.getTitle());
            postscript.setContent(request.getContent());
            postscript.setImage(request.getImage());
            postscript.setKind(request.getKind());
            postscript.setGugun(request.getGugun());
            postscript.setSido(request.getSido());

            System.out.println(postscript);
            postscriptDao.save(postscript);

            postpicDao.deleteByPostscriptno(postscript.getPostscriptno());

            List<Postpic> postpicList = new ArrayList<>();
            if(!postpicList.isEmpty()){
                for (MultipartFile file : images) {
                    final String originalfileName = file.getOriginalFilename();
                    final String filepath = "C:/Image/" + originalfileName;
                    final File dest = new File(filepath);
                    file.transferTo(dest);
                    Postpic postpic = new Postpic();
                    postpic.setPostscriptno(postscript.getPostscriptno());
                    postpic.setPostpath(filepath);
                    postpicList.add(postpic);
                }
            }

            postpicDao.saveAll(postpicList);

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

}