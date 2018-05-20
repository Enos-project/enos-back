package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.Installation;
import com.enos.enos.entity.User;

public interface InstallationRepository extends JpaRepository<Installation, Long> {

    public List<Installation> findByUser(User user);

    public Optional<Installation> findByUserAndApplication(User user, Application application);
}