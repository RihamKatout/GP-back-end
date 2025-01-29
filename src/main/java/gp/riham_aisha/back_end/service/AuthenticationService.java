package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.auth.AuthenticationResponse;
import gp.riham_aisha.back_end.dto.auth.LoginRequest;
import gp.riham_aisha.back_end.dto.auth.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    User getCurrentUser();
    AuthenticationResponse authenticate(LoginRequest request) throws AuthenticationException;

    @Transactional
    AuthenticationResponse register(RegistrationRequest request);
}
