package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.AuthenticationService;
import com.Graduation_Be.service.impl.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse Authenticated(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getUserPassword()
                    )
            );

            String token = jwtTokenService.generateToken(authentication);

            return AuthenticationResponse.builder()
                    .token(token)
                    .LoginStatus(true)
                    .build();
        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .LoginStatus(false)
                    .build();
        }
    }


}