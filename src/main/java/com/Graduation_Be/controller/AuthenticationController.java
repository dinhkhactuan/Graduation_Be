package com.Graduation_Be.controller;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceIml;


    @PostMapping(value = "/login")
    public AuthenticationResponse login (@RequestBody  AuthenticationRequest authenticationRequest){
        return authenticationServiceIml.Authenticated(authenticationRequest);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout (){
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().body(Map.of("message","Đăng xuất thành công"));
    }
}
