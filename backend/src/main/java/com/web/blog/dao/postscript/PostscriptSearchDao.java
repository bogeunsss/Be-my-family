package com.web.blog.dao.postscript;

import java.util.List;
import javax.transaction.Transactional;
import com.web.blog.model.postscript.Postscript;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostscriptSearchDao extends JpaRepository<Postscript, String> {
    List<Postscript> findByUidContainingOrderByPostscriptnoDesc(String searchText);
    List<Postscript> findByTitleContainingOrderByPostscriptnoDesc(String searchText);
}