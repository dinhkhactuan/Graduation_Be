package com.Graduation_Be.controller;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceIml;


    @PostMapping(value = "/login")
    public AuthenticationResponse abc(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationServiceIml.Authenticated(authenticationRequest);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().body(Map.of("message", "Đăng xuất thành công"));
    }
}
