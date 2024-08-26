package com.Graduation_Be.controller;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceIml;

    @PostMapping(value = "/login")
    public AuthenticationResponse abc (@RequestBody  AuthenticationRequest authenticationRequest){
        return authenticationServiceIml.Authenticated(authenticationRequest);
    }
}
