package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.enos.enos.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    public List<Application> findByType(String applicationType);    
}