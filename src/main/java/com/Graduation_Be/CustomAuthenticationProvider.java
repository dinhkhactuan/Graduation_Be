package com.Graduation_Be;

import com.Graduation_Be.model.UserEntity;
import com.Graduation_Be.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String pwd = auth.getCredentials().toString();
        if ("admin".equals(username) && "123456".equals(pwd)) {
            return new UsernamePasswordAuthenticationToken(username, pwd, Collections.emptyList());
        } else {
            throw new BadCredentialsException("User authentication failed!!!!");
        }
    }
    @Override
    public boolean supports(Class<?>auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}