package com.enos.enos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.Installation;
import com.enos.enos.entity.User;
import com.enos.enos.entity.enums.EApplicationType;
import com.enos.enos.repository.ApplicationRepository;
import com.enos.enos.repository.InstallationRepository;
import com.enos.enos.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/secured/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplication(@PathVariable(value = "applicationId") long applicationId) {
        
        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(application.get(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Application>> getInstalledApplications(@PathVariable(value = "userId") long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<Installation> installationList = installationRepository.findByUser(user.get());

        List<Application> applicationList = installationList.stream().map(installation -> installation.getApplication()).collect(Collectors.toList());

        if(applicationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }

    @GetMapping("/type/{applicationType}")
    public ResponseEntity<List<Application>> getApplicationsByType(@PathVariable(value = "applicationType") EApplicationType type) {

        List<Application> applicationList = applicationRepository.findByType(type.toString());

        if(applicationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }
}