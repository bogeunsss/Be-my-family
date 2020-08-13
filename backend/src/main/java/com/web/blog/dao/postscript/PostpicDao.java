package com.web.blog.dao.postscript;

import com.web.blog.model.postscript.Postpic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostpicDao extends JpaRepository<Postpic, String> {
    
    Postpic save(Postpic postpic);

    @Transactional
    void deleteByPostscriptno(int postscriptno);

}