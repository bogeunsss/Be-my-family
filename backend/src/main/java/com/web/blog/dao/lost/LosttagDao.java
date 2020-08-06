package com.web.blog.dao.lost;

import com.web.blog.model.lost.Losttag;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface LosttagDao extends JpaRepository<Losttag, String> {
    
    List<Losttag> findByLostno(int lostno);

    @Transactional
    void deleteByLostno(int lostno);

}
