package com.enos.enos.controller;

import java.util.List;
import java.util.Optional;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.Param;
import com.enos.enos.entity.User;
import com.enos.enos.repository.ApplicationRepository;
import com.enos.enos.repository.ParamRepository;
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
@RequestMapping("/secured/param")
public class ParamController {

    @Autowired
    private ParamRepository paramRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/user/{userId}/application/{applicationId}")
    public ResponseEntity<List<Param>> getParamsForUserAndApplication(@PathVariable(value = "userId") long userId, 
        @PathVariable(value = "applicationId") long applicationId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Param> paramList = paramRepository.findByUserAndApplication(user.get(), application.get());

        if(paramList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(paramList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/application/{applicationId}/key/{key}")
    public ResponseEntity<Param> getParamForUserAndApplicationAndParamKey(@PathVariable(value = "userId") long userId,
        @PathVariable(value = "applicationId") long applicationId, @PathVariable(value = "key") String key) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Param param = paramRepository.findByUserAndApplicationAndKey(user.get(), application.get(), key);

        if(param == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(param, HttpStatus.OK);
    }
}