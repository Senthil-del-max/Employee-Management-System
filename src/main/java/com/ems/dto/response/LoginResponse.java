package com.ems.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String token;
    private String fullName;
    private String email;
    private String role;
    private String message;
}