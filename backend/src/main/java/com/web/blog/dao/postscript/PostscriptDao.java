package com.web.blog.dao.postscript;

import java.util.Optional;
import javax.transaction.Transactional;
import com.web.blog.model.postscript.Postscript;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostscriptDao extends JpaRepository<Postscript, String> {
    Optional<Postscript> findPostscriptByUid(String uid);
    String findByUid(String uid);
    Postscript save (Postscript postscript);
    Optional<Postscript> findByPostscriptno(Integer postscriptno);
    @Transactional
    Void deleteByPostscriptno(Integer postscriptno);
    Postscript getPostscriptByPostscriptno(Integer postscriptno);
}