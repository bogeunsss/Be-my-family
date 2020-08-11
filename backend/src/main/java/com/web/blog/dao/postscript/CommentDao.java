package com.web.blog.dao.postscript;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.web.blog.model.postscript.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, String> {
        Comment save (Comment comment);
        List<Comment> findByPostscriptnoOrderByPostscriptnoDesc(Integer postscriptno);
}