package com.web.blog.dao.main;

import java.util.List;

import com.web.blog.model.lost.Lost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainLostDao extends JpaRepository<Lost, String> {
    List<Lost> findTop4ByOrderByLostnoDesc();
}