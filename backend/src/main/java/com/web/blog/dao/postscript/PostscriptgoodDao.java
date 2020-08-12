package com.web.blog.dao.postscript;

import com.web.blog.model.postscript.Postscriptgood;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostscriptgoodDao extends JpaRepository<Postscriptgood, String>{
    Integer findByUidAndPostscriptno(String uid, int postscriptno);
    Integer countByPostscriptno(int postscriptno);
}