package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enos.enos.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
}