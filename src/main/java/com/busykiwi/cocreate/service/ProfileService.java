package com.busykiwi.cocreate.service;

import com.busykiwi.cocreate.dto.ProfileViewResponse;
import com.busykiwi.cocreate.model.Project;
import com.busykiwi.cocreate.model.Skill;
import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.model.UserProfile;
import com.busykiwi.cocreate.repository.ProfileRespository;
import com.busykiwi.cocreate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRespository profileRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public User getDetails() {
        User user = authService.getCurrentUser();
        return userRepository.findById(user.getId()).orElse(new User());
    }

    public UserProfile getProfile() {
        User user = authService.getCurrentUser();
        return profileRespository.getUserProfileByUserId(user.getId());
    }
}
