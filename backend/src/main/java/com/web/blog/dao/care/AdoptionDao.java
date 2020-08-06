package com.web.blog.dao.care;

import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import com.web.blog.model.adoption.Adoption;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionDao extends JpaRepository<Adoption, String>{
    // Optional<Adoption> findByUid(String uid);
    Optional<Adoption> findByDesertionno(String desertionno);
    Optional<Adoption> findByUidAndDesertionno(String uid, String desertionno);
    @Transactional
    void deleteByUidAndDesertionno(String uid, String desertionno);
    String getAdoptionByUid(String uid);
    List<Adoption> findByUid(String uid);
    Adoption save(Adoption adoption);
}