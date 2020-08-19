package com.web.blog.dao.postscript;

import java.util.Optional;

import javax.transaction.Transactional;

import com.web.blog.model.postscript.Postscriptgood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


public interface PostscriptgoodDao extends JpaRepository<Postscriptgood, String>{
    
    Optional<Postscriptgood> findByUidAndPostscriptno(String uid, int postscriptno);
    Integer countByPostscriptno(int postscriptno);
    
}