package com.web.blog.dao.care;

import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InterestDao extends JpaRepository<Interest, String> {
    @Transactional
    void deleteByInterestno(int interestno);
    Interest save(Interest interest);
    List<Interest> findByUid(String uid);
    Optional<Interest> findByUidAndDesertionno(String uid, String desertionno);
    List<Interest> findByUidOrderByInterestno(String uid);
}