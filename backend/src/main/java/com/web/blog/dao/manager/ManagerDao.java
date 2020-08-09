package com.web.blog.dao.manager;

import java.util.Optional;

import com.web.blog.model.manager.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerDao extends JpaRepository<Manager, String> {
    Manager getManagerByEmail(String email);
    Manager getManagerByMid(String mid);
    Manager save(Manager manager);
    
    Optional<Manager> findManagerByEmailAndPassword(String email, String password);
}