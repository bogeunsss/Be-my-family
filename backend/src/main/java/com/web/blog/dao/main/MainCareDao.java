package com.web.blog.dao.main;

import java.util.List;

import com.web.blog.model.care.Careboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCareDao extends JpaRepository<Careboard, String> {
    List<Careboard> findTop4ByOrderByNoticeedtDesc();
}