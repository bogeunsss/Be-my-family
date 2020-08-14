package com.web.blog.dao.care;

import com.web.blog.model.care.CareRecommend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareRecommendDao extends JpaRepository<CareRecommend, String> {
    Page<CareRecommend> findAll(Pageable pageable);
    Page<CareRecommend> findByCareaddrContaining(String searchText, Pageable pageable);
    Page<CareRecommend> findBykindcdContaining(String searchText, Pageable pageable);
}