package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.enos.enos.entity.Installation;
import com.enos.enos.entity.Param;

public interface ParamRepository extends JpaRepository<Param, Long> {

    public List<Param> findByInstallation(Installation installation);

    public Optional<Param> findByInstallationAndKey(Installation installation, String key);
}