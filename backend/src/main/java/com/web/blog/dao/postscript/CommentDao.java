package com.web.blog.dao.postscript;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.web.blog.model.postscript.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CommentDao extends JpaRepository<Comment, String> {
        
        Comment save (Comment comment);
        List<Comment> findByPostscriptnoOrderByPostscriptno(Integer postscriptno);
        void deleteByCommentnoAndUid(int commentno, String Uid);
        Optional<Comment> findByCommentnoAndUid(int commentno, String Uid);

}