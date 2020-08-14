package com.web.blog.dao.lost;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.lost.Lost;
import com.web.blog.model.lost.Losttag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LostDao extends JpaRepository<Lost, String> {
    
    Optional<Lost> findByLostno(int lostno);

    Optional<Lost> findByLostnoAndUid(int lostno, String uid);   

    @Query(value = "SELECT * FROM lost WHERE lostno IN (:lostnoList)", nativeQuery = true)
    List<Lost> findByLostnoList(@Param("lostnoList") List<Integer> lostnoList);

    @Query(value = "SELECT * FROM lost WHERE losttype IN(:matchType) AND lostBreed = :lostBreed AND lostsido = :lostSido", nativeQuery = true)
    List<Lost> findMatch(List<String> matchType, String lostSido, String lostBreed);
    
    @Transactional
    void deleteByLostno(int lostno);
   
}