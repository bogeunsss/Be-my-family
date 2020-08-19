package com.web.blog.dao.postscript;

import java.util.List;
import javax.transaction.Transactional;
import com.web.blog.model.postscript.Postscript;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostscriptSearchDao extends JpaRepository<Postscript, String> {
    
    Page<Postscript> findByUidContainingOrderByPostscriptnoDesc(String searchText, Pageable pageable);
    Page<Postscript> findByTitleContainingOrderByPostscriptnoDesc(String searchText, Pageable pageable);
}