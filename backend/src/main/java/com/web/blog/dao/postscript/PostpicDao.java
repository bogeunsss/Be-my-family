package com.web.blog.dao.postscript;

import java.util.List;

import com.web.blog.model.postscript.Postpic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostpicDao extends JpaRepository<Postpic, String> {
    
    Postpic save(Postpic postpic);

    @Query(value = "SELECT * FROM postpic GROUP BY postscriptno order by postscriptno desc", nativeQuery = true)
    List<Postpic> findPic();

    @Transactional
    void deleteByPostscriptno(int postscriptno);

    List<Postpic> findByPostscriptno(int postscriptno);

}