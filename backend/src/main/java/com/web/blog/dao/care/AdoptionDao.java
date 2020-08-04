package com.web.blog.dao.care;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.care.Adoption;
import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionDao extends JpaRepository<Adoption, String>{
    // Optional<Adoption> findByUid(String uid);
    Optional<Adoption> findByDesertionno(String desertionNo);
    List<Adoption> findByUid(String uid);
}