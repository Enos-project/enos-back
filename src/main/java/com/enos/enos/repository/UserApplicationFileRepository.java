package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.User;
import com.enos.enos.entity.UserApplicationFile;

public interface UserApplicationFileRepository extends JpaRepository<UserApplicationFile, Long> {

    public List<UserApplicationFile> findByUserAndApplication(User user, Application application);

    public List<UserApplicationFile> findByUser(User user);
}