package com.busykiwi.cocreate.controller;


import com.busykiwi.cocreate.dto.ApiResponse;
import com.busykiwi.cocreate.dto.CustomResponse;
import com.busykiwi.cocreate.dto.ProfileViewResponse;
import com.busykiwi.cocreate.model.UserProfile;
import com.busykiwi.cocreate.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("view")
    public ResponseEntity<?> viewProfile() {
        UserProfile userProfile = profileService.getProfile();
        if (userProfile != null) {
            ProfileViewResponse profileViewResponse = new ProfileViewResponse(userProfile.getId(), userProfile.getBio());
            return new ResponseEntity<>(new ApiResponse<ProfileViewResponse>(true, "success", profileViewResponse), HttpStatus.OK);
        } else {
            CustomResponse response = new CustomResponse(
                    "error", "Profile not created"
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
