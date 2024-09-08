package com.Graduation_Be.controller;

import com.Graduation_Be.api.ApiResponse;
import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.respone.UserResponseDto;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.mapper.UserMapper;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImpl authenticationServiceIml;

    @Autowired
    private UserMapper userMapper ;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("sub");
        Optional<UserResponseDto> user = userMapper.toOptionalUserRespone(userRepository.findByUserName(username));
        if (user != null) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
