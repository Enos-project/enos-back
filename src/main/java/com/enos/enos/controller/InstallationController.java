package com.enos.enos.controller;

import java.util.List;
import java.util.Optional;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.Installation;
import com.enos.enos.entity.User;
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
@RequestMapping("/secured/installation")
public class InstallationController {

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Installation>> getInstallationsForUser(@PathVariable(value = "userId") long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Installation> installationList = installationRepository.findByUser(user.get());

        if(installationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(installationList, HttpStatus.OK);
    }

    @GetMapping("/isintalled/user/{userId}/application/{applicationId}")
    public ResponseEntity<Boolean> isAppInstalled(@PathVariable(value = "userId") long userId,
        @PathVariable(value = "applicationId") long applicationId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Installation> installation = installationRepository.findByUserAndApplication(user.get(), application.get());

        return new ResponseEntity<>(installation.isPresent(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/application/{applicationId}")
    public ResponseEntity<Installation> getInstallation(@PathVariable(value = "userId") long userId,
        @PathVariable(value = "applicationId") long applicationId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Installation> installation = installationRepository.findByUserAndApplication(user.get(), application.get());

        if(!installation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(installation.get(), HttpStatus.OK);
    }
}