package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enos.enos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}