package com.busykiwi.cocreate.service;

import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public User addUser(User user) {
        return authRepository.save(user);
    }
}
