package com.web.blog.dao.postscript;

import java.util.Optional;
import javax.transaction.Transactional;
import com.web.blog.model.postscript.Postscript;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PostscriptDao extends JpaRepository<Postscript, String> {
    
    Optional<Postscript> findPostscriptByUid(String uid);
    String findByUid(String uid);
    Postscript save (Postscript postscript);
    
    Optional<Postscript> findByPostscriptno(int postscriptno);
    Optional<Postscript> findByPostscriptnoAndUid(int postscriptno, String uid);
    
    @Transactional
    @Modifying
    void deleteByPostscriptno(int postscriptno);
    
    Postscript getPostscriptByPostscriptno(int postscriptno);
}