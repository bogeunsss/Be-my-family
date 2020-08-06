package com.web.blog.dao.postscript;

import com.web.blog.model.postscript.Postscript;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostscriptDao extends JpaRepository<Postscript, String> {
    Postscript save (Postscript postscript);
}