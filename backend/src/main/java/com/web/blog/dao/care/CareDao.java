
package com.web.blog.dao.care;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.care.Careboard;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareDao extends JpaRepository<Careboard, String> {

    List<Careboard> findByCareaddrContainingOrderByNoticesdtDesc(String searchText);
    List<Careboard> findBykindcdContainingOrderByNoticesdtDesc(String searchText);
    Careboard findByDesertionno(String desertionno);
}
