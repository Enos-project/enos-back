package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.enos.enos.entity.Installation;
import com.enos.enos.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

    public List<Log> findByInstallation(Installation installation);
}
