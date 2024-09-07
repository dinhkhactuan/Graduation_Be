package com.Graduation_Be.service.impl;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.exception.exceptionOption.ResourceNotFoundException;
import com.Graduation_Be.model.RoleEntity;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.RoleRepository;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.AuthenticationService;
import com.Graduation_Be.service.impl.jwt.JwtTokenService;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse Authenticated(AuthenticationRequest authenticationRequest) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByUserName(authenticationRequest.getUserName());
            if(optionalUser.isEmpty()){
                throw new ResourceNotFoundException(MessageSys.NOT_FOUND);
            }
            UserEntity userEntity =optionalUser.get();
            Optional<RoleEntity> optionalRole =roleRepository.findById(authenticationRequest.getRoleId());
            if(optionalRole.isEmpty()){
                throw new ResourceNotFoundException(MessageSys.NOT_FOUND);
            }
            if(!Objects.equals(authenticationRequest.getRoleId(),userEntity.getRoleEntity().getRoleId())){
                throw new ResourceNotFoundException(MessageSys.NOT_FOUND);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getUserPassword()
                    )
            );

            UserEntity user = userRepository.findByUserName(authenticationRequest.getUserName())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String role = Optional.ofNullable(user.getRoleEntity())
                    .map(RoleEntity::getRoleCode)
                    .orElse("ROLE_USER");

            Optional<RoleEntity> roleEntity = roleRepository.findById(authenticationRequest.getRoleId());
            String token = jwtTokenService.generateToken(user.getUserName(),role);

            return AuthenticationResponse.builder()
                    .token(token)
                    .LoginStatus(true)
                    .roleCode(roleEntity.get().getRoleCode())
                    .build();
        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .LoginStatus(false)
                    .token(null)
                    .build();
        }
    }


}