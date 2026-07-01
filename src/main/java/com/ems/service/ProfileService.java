package com.ems.service;

import com.ems.dto.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse getProfile(String email);

}