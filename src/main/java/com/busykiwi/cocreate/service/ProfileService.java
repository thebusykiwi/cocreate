package com.busykiwi.cocreate.service;

import com.busykiwi.cocreate.dto.ProfileViewResponse;
import com.busykiwi.cocreate.model.Skill;
import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.model.UserProfile;
import com.busykiwi.cocreate.repository.ProfileRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRespository profileRespository;

    @Autowired
    private AuthService authService;

    public ProfileViewResponse getProfile() {
        User user = authService.getCurrentUser();
        UserProfile profile = profileRespository.getUserProfileByUserId(user.getId());
        ProfileViewResponse profileViewResponse = new ProfileViewResponse(
                user.getId(), user.getName(), user.getUsername(), profile.getBio()
        );
        return profileViewResponse;
    }
}
