package com.web.blog.dao.lost;

import com.web.blog.model.lost.Losttag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface LosttagDao extends JpaRepository<Losttag, String> {
    
    List<Losttag> findByLostno(int lostno);

    @Query(value = "SELECT * FROM losttag WHERE tagname IN (:tags) GROUP BY lostno having count(lostno)>=:size", nativeQuery = true)
    List<Losttag> findtag(@Param("tags") List<String> tags, int size);

    @Transactional
    @Modifying
    void deleteByLostno(int lostno);

}
