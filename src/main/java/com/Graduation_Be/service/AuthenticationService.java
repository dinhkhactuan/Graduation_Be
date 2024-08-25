package com.Graduation_Be.service;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;
import org.springframework.stereotype.Service;

public interface AuthenticationService {

    public AuthenticationResponse Authenticated (AuthenticationRequest authenticationRequest);
}
