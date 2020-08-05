package com.web.blog.dao.care;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;



public interface InterestDao extends JpaRepository<Interest, String> {
    @Transactional
    void deleteByInterestno(int interestno);
    Optional<Interest> findByUidAndDesertionno(String uid, String desertionNo);
    Interest save(Interest interest);
    List<Interest> findByUid(String uid);
}
