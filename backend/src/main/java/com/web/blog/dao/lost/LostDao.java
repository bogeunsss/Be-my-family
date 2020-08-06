package com.web.blog.dao.lost;

import java.util.Optional;

import com.web.blog.model.lost.Lost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LostDao extends JpaRepository<Lost, String> {
    
    Optional<Lost> findByLostno(int lostno);

}
