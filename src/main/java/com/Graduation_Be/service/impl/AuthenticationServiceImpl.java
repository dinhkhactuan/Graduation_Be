package com.Graduation_Be.service.impl;

import com.Graduation_Be.components.JwtTokenUtils;
import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import com.Graduation_Be.exception.exceptionOption.InternalServerException;
import com.Graduation_Be.model.RoleEntity;
import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.RoleRepository;
import com.Graduation_Be.repository.UserRepository;
import com.Graduation_Be.service.AuthenticationService;
import com.Graduation_Be.service.impl.jwt.JwtTokenService;
import com.Graduation_Be.shard.enums.MessageSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;


    @Override
    public AuthenticationResponse Authenticated(AuthenticationRequest authenticationRequest) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByUserName(authenticationRequest.getUserName());
            if (optionalUser.isEmpty()) {
                throw new InternalServerException(MessageSys.NOT_FOUND);
            }
            UserEntity existingUser = optionalUser.get();
            Optional<RoleEntity> optionalRole = roleRepository.findById(authenticationRequest.getRoleId());
            if (optionalRole.isEmpty()) {
                throw new InternalServerException(MessageSys.NOT_FOUND);
            }

            if (!Objects.equals(authenticationRequest.getRoleId(), existingUser.getRoleEntity().getRoleId())) {
                throw new InternalServerException(MessageSys.NOT_FOUND);
            }

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
                    .roleCode(optionalRole.get().getRoleCode())
                    .build();
        } catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .LoginStatus(false)
                    .build();
        }
    }

    @Override
    public Map<String, String> login(String userName, String password, Long roleId) throws Exception {
       /* Optional<UserEntity> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isEmpty()) {
            throw new InternalServerException(MessageSys.NOT_FOUND);
        }
        //return optionalUser.get();//muốn trả JWT token ?
        UserEntity existingUser = optionalUser.get();
        Optional<RoleEntity> optionalRole = roleRepository.findById(roleId);
//        if (optionalRole.isEmpty() || !roleId.equals(existingUser.getRoleEntity().getRoleId())) {
        if (optionalRole.isEmpty()) {
            throw new InternalServerException(MessageSys.NOT_FOUND);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userName, password, existingUser.getAuthorities()
        );

        if(!Objects.equals(roleId, optionalRole.get().getRoleId())){
            throw new InternalServerException(MessageSys.NOT_FOUND);
        }

        authenticationManager.authenticate(authenticationToken);
        Map<String, String> token = new HashMap<>();
        token.put("token", jwtTokenUtil.generateToken(existingUser));
        token.put("roleCode", optionalRole.get().getRoleCode());

        return token;*/
        return null;
    }


}