package com.web.blog.dao.lost;

import java.util.Optional;

import com.web.blog.model.lost.Lost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LostDao extends JpaRepository<Lost, String> {
    
    Optional<Lost> findByLostno(int lostno);

    Optional<Lost> findByLostnoAndUid(int lostno, String uid);
    
    @Transactional
    void deleteByLostno(int lostno);

}
