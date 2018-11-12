package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.enos.enos.entity.Installation;
import com.enos.enos.entity.AppFile;

public interface AppFileRepository extends JpaRepository<AppFile, Long> {

    public List<AppFile> findByInstallation(Installation installation);
}