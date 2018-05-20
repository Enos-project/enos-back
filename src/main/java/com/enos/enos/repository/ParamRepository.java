package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.Param;
import com.enos.enos.entity.User;

public interface ParamRepository extends JpaRepository<Param, Long> {

    public List<Param> findByUserAndApplication(User user, Application application);

    public Param findByUserAndApplicationAndKey(User user, Application application, String key);
}