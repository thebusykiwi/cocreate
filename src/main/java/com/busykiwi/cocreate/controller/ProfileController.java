package com.busykiwi.cocreate.controller;


import com.busykiwi.cocreate.dto.ApiResponse;
import com.busykiwi.cocreate.dto.CustomResponse;
import com.busykiwi.cocreate.dto.ProfileViewResponse;
import com.busykiwi.cocreate.model.Project;
import com.busykiwi.cocreate.model.Skill;
import com.busykiwi.cocreate.model.User;
import com.busykiwi.cocreate.model.UserProfile;
import com.busykiwi.cocreate.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("view")
    public ResponseEntity<?> viewProfile() {
        User user = profileService.getDetails();
        UserProfile profile = profileService.getProfile();
        if (user != null && profile != null) {
            List<Skill> skills = user.getSkills();
            List<Project> projects = user.getProjects();
            ProfileViewResponse profileViewResponse = new ProfileViewResponse(user.getId(), profile.getBio(), skills, projects);
            return new ResponseEntity<>(new ApiResponse<ProfileViewResponse>(true, "success", profileViewResponse), HttpStatus.OK);
        } else {
            CustomResponse response = new CustomResponse(
                    "error", "Profile not created"
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("update")
//    public ResponseEntity<?> updateProfile(@RequestBody ) {
//
//    }
}
