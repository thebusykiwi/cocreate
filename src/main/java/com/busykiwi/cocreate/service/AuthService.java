package com.busykiwi.cocreate.service;

import com.busykiwi.cocreate.dto.LoginRequest;
import com.busykiwi.cocreate.model.LoginStatus;
import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.model.UserProfile;
import com.busykiwi.cocreate.repository.AuthRepository;
import com.busykiwi.cocreate.repository.ProfileRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private ProfileRespository profileRespository;
    private User currentUser;

    public User addUser(User user) {
        User savedUser = authRepository.save(user);
        UserProfile userProfile = new UserProfile();
        userProfile.setBio("");
        userProfile.setUser(savedUser);
        profileRespository.save(userProfile);
        return savedUser;
    }

    public LoginStatus checkCredentials(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String pass = loginRequest.getPassword();
        User userExist = authRepository.findUserByEmail(email);
        if (userExist != null) {
            if (userExist.getPassword().equals(pass)) {
                currentUser = userExist;
                return LoginStatus.SUCCESS;
            } else {
                return LoginStatus.INVALID_PASSWORD;
            }
        }
        return LoginStatus.USER_NOT_FOUND;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
