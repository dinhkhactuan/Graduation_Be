package com.Graduation_Be.service;

import com.Graduation_Be.dto.respone.AuthenticationResponse;
import com.Graduation_Be.dto.resquest.AuthenticationRequest;

import java.util.Map;

public interface AuthenticationService {

    AuthenticationResponse Authenticated (AuthenticationRequest authenticationRequest);

    Map<String, String> login(String userName, String password, Long roleId) throws Exception;
}
