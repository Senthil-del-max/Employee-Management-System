package com.ems.controller;

import com.ems.dto.response.ProfileResponse;
import com.ems.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{email}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable String email) {

        return ResponseEntity.ok(
                profileService.getProfile(email)
        );

    }
}