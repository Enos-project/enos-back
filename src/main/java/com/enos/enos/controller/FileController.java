package com.enos.enos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.ApplicationStaticFile;
import com.enos.enos.entity.File;
import com.enos.enos.entity.User;
import com.enos.enos.entity.UserApplicationFile;
import com.enos.enos.repository.ApplicationRepository;
import com.enos.enos.repository.ApplicationStaticFileRepository;
import com.enos.enos.repository.FileRepository;
import com.enos.enos.repository.UserApplicationFileRepository;
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
@RequestMapping("/secured/file")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ApplicationStaticFileRepository applicationStaticFileRepository;

    @Autowired
    private UserApplicationFileRepository userApplicationFileRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{fileId}")
    public ResponseEntity<File> getFile(@PathVariable(value = "fileId") long fileId) {

        Optional<File> file = fileRepository.findById(fileId);

        if(file.isPresent()) {
            return new ResponseEntity<>(file.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<File>> getApplicationStaticFiles(@PathVariable(value = "applicationId") long applicationId) {

        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ApplicationStaticFile> applicationStaticFileList = applicationStaticFileRepository.findByApplication(application.get());

        List<File> fileList = applicationStaticFileList.stream().map(aes -> aes.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/application/{applicationId}")
    public ResponseEntity<List<File>> getUserApplicationFiles(@PathVariable(value = "userId") long userId,
        @PathVariable(value = "applicationId") long applicationId) {

        Optional<Application> application = applicationRepository.findById(applicationId);
        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserApplicationFile> userApplicationFileList = userApplicationFileRepository.findByUserAndApplication(user.get(), application.get());

        List<File> fileList = userApplicationFileList.stream().map(uaf -> uaf.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<File>> getAllUserApplicationFiles(@PathVariable(value = "userId") long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserApplicationFile> userApplicationFileList = userApplicationFileRepository.findByUser(user.get());

        List<File> fileList = userApplicationFileList.stream().map(uaf -> uaf.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }
}