package com.web.blog.dao.postscript;

import com.web.blog.model.postscript.Postscript;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostscriptPageDao extends JpaRepository<Postscript, String> {
    
    Page<Postscript> findAll(Pageable pageable);

}