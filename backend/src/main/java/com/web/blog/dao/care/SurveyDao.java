package com.web.blog.dao.care;

import java.util.Optional;
import java.util.List;

import com.web.blog.model.care.Careboard;
import com.web.blog.model.care.Survey;

import org.hibernate.annotations.OrderBy; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyDao extends JpaRepository<Survey, String> { 

    Optional<Survey> findByUid(String uid);
}