package com.web.blog.dao.manager;

import com.web.blog.model.manager.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDao extends JpaRepository<Manager, String> {
    
}