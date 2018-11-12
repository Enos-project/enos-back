package com.enos.enos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.enos.enos.entity.Installation;
import com.enos.enos.entity.Log;
import com.enos.enos.repository.InstallationRepository;
import com.enos.enos.repository.LogRepository;
import com.enos.enos.utils.PojoTransformer;
import com.enos.enos.pojo.entity.LogPojo;

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
@RequestMapping("/secured/log")
public class LogController {

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/log/{logId}")
    public ResponseEntity<LogPojo> getLog(@PathVariable(value = "logId") long logId) {

        Optional<Log> log = logRepository.findById(logId);

        if(!log.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(PojoTransformer.getLogPojoFromLog(log.get()), HttpStatus.OK);
    }

    @GetMapping("/installation/{installationId}/logs")
    public ResponseEntity<List<LogPojo>> getLogsForInstallation(@PathVariable(value = "installationId") long installationId) {

        Optional<Installation> installation = installationRepository.findById(installationId);

        if(!installation.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Log> logList = logRepository.findByInstallation(installation.get());

        if(logList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<LogPojo> logPojoList = logList.stream().map(PojoTransformer::getLogPojoFromLog).collect(Collectors.toList());

        return new ResponseEntity<>(logPojoList, HttpStatus.OK);
    }
}
