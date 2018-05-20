package com.enos.enos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import javax.servlet.ServletException;

import com.enos.enos.entity.User;
import com.enos.enos.repository.UserRepository;
import com.enos.enos.utils.AuthenticationUtils;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) throws ServletException {

		User user = userRepository.findByUsername(username);

		if(user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String encryptedPassword = AuthenticationUtils.getEncryptedPassword(password, user.getSalt());
		String encryptedUserPassword = user.getPassword();

		if (!encryptedPassword.equals(encryptedUserPassword)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
			.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	}
}
