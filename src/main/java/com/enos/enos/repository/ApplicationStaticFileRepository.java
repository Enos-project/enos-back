package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.ApplicationStaticFile;

public interface ApplicationStaticFileRepository extends JpaRepository<ApplicationStaticFile, Long> {

    public List<ApplicationStaticFile> findByApplication(Application application);
}