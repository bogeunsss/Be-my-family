package com.web.blog.dao.lost;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.lost.Lostreply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface LostreplyDao extends JpaRepository<Lostreply, String> {
    
    List<Lostreply> findByLostnoOrderByCreatedate(int lostno);

    Optional<Lostreply> findByLostreplyno(int lostreplyno);

    Optional<Lostreply> findByLostreplynoAndUid(int lostreplyno, String uid);

    @Transactional
    @Modifying
    void deleteByLostreplynoAndUid(int lostreplyno, String uid);

    @Transactional
    @Modifying
    void deleteByLostno(int lostno);

}