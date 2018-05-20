package com.enos.enos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enos.enos.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}