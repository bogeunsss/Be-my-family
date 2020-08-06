package com.web.blog.dao.care;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Interest;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaredetailDao extends JpaRepository<Careboard, String>{
    Optional<Careboard> findByDesertionno(String desertionno);

}