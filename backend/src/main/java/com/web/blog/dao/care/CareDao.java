
package com.web.blog.dao.care;

import com.web.blog.model.care.Careboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareDao extends JpaRepository<Careboard, String> {

    Page<Careboard> findAll(Pageable pageable);
    Page<Careboard> findByCareaddrContaining(String searchText, Pageable pageable);
    Page<Careboard> findBykindcdContaining(String searchText, Pageable pageable);

    Careboard findByDesertionno(String desertionno);
}