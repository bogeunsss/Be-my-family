package com.web.blog.dao.lost;

import com.web.blog.model.lost.Losttag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LosttagDao extends JpaRepository<Losttag, String> {
    
}
