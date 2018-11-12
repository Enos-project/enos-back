package com.enos.enos.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.enos.enos.entity.Application;
import com.enos.enos.entity.ApplicationStaticFile;
import com.enos.enos.entity.File;
import com.enos.enos.entity.User;
import com.enos.enos.entity.Installation;
import com.enos.enos.entity.AppFile;
import com.enos.enos.repository.AppFileRepository;
import com.enos.enos.repository.ApplicationRepository;
import com.enos.enos.repository.ApplicationStaticFileRepository;
import com.enos.enos.repository.FileRepository;
import com.enos.enos.repository.InstallationRepository;
import com.enos.enos.repository.UserRepository;
import com.enos.enos.utils.PojoTransformer;
import com.enos.enos.pojo.entity.FilePojo;

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
    private AppFileRepository appFileRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstallationRepository installationRepository;

    @GetMapping("/{fileId}")
    public ResponseEntity<FilePojo> getFile(@PathVariable(value = "fileId") long fileId) {

        Optional<File> file = fileRepository.findById(fileId);

        if(file.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(PojoTransformer.getFilePojoFromFile(file.get()), HttpStatus.OK);
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<FilePojo>> getApplicationStaticFiles(@PathVariable(value = "applicationId") long applicationId) {

        Optional<Application> application = applicationRepository.findById(applicationId);

        if(!application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ApplicationStaticFile> applicationStaticFileList = applicationStaticFileRepository.findByApplication(application.get());

        List<File> fileList = applicationStaticFileList.stream().map(aes -> aes.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FilePojo> filePojoList = fileList.stream().map(PojoTransformer::getFilePojoFromFile).collect(Collectors.toList());

        return new ResponseEntity<>(filePojoList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/application/{applicationId}")
    public ResponseEntity<List<FilePojo>> getAppFiles(@PathVariable(value = "userId") long userId,
        @PathVariable(value = "applicationId") long applicationId) {

        Optional<Application> application = applicationRepository.findById(applicationId);
        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent() || !application.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Optional<Installation> installation = installationRepository.findByUserAndApplication(user.get(), application.get());

        if(!installation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AppFile> appFileList = appFileRepository.findByInstallation(installation.get());

        List<File> fileList = appFileList.stream().map(uaf -> uaf.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FilePojo> filePojoList = fileList.stream().map(PojoTransformer::getFilePojoFromFile).collect(Collectors.toList());

        return new ResponseEntity<>(filePojoList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FilePojo>> getAllUserApplicationFiles(@PathVariable(value = "userId") long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Installation> installationList = installationRepository.findByUser(user.get());

        if(installationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AppFile> appFileList = new ArrayList<>();

        for(Installation installation : installationList) {
            appFileList.addAll(appFileRepository.findByInstallation(installation));
        }

        List<File> fileList = appFileList.stream().map(uaf -> uaf.getFile()).collect(Collectors.toList());

        if(fileList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FilePojo> filePojoList = fileList.stream().map(PojoTransformer::getFilePojoFromFile).collect(Collectors.toList());

        return new ResponseEntity<>(filePojoList, HttpStatus.OK);
    }
}