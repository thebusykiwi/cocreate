package com.busykiwi.cocreate.controller;

import com.busykiwi.cocreate.dto.LoginRequest;
import com.busykiwi.cocreate.dto.LoginResponse;
import com.busykiwi.cocreate.model.LoginStatus;
import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@ModelAttribute User user) {
        User newUser = null;
        try {
            newUser = authService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginStatus status = authService.checkCredentials(loginRequest);

        LoginResponse response = new LoginResponse();

        if (status == LoginStatus.SUCCESS) {
            response.setStatus(status);
            response.setMessage("Login successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if (status == LoginStatus.INVALID_PASSWORD) {
            response.setStatus(status);
            response.setMessage("Invalid password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else{
            response.setStatus(status);
            response.setMessage("User not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
