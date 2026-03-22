package com.busykiwi.cocreate.controller;

import com.busykiwi.cocreate.dto.LoginRequest;
import com.busykiwi.cocreate.dto.LoginResponse;
import com.busykiwi.cocreate.dto.RegisterRequest;
import com.busykiwi.cocreate.dto.RegisterResponse;
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
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());
        try {
            newUser = authService.addUser(newUser);
            RegisterResponse response = new RegisterResponse();
            response.setId(newUser.getId());
            response.setName(newUser.getName());
            response.setUsername(newUser.getUsername());
            response.setEmail(newUser.getEmail());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisterResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
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
