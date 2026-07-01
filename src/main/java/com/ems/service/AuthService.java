package com.ems.service;

import com.ems.dto.request.LoginRequest;
import com.ems.dto.request.RegisterRequest;
import com.ems.dto.response.LoginResponse;
import com.ems.entity.User;

public interface AuthService {

    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}