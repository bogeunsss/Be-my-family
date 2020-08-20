package com.web.blog.dao.main;

import java.util.Optional;

import com.web.blog.model.postscript.Postpic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainPostpicDao extends JpaRepository<Postpic, String> {
    Postpic findTop1ByPostscriptno(int postscriptno);
}