package com.web.blog.dao.main;

import java.util.List;

import com.web.blog.model.postscript.Postscript;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainPostscriptDao extends JpaRepository<Postscript, String> {
    List<Postscript> findTop4ByOrderByPostscriptnoDesc();
}