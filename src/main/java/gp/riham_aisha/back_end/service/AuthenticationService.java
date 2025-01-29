package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.AuthenticationResponse;
import gp.riham_aisha.back_end.dto.LoginRequest;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AuthenticationService {
    User getCurrentUser();
    AuthenticationResponse authenticate(LoginRequest request) throws AuthenticationException;

    @Transactional
    AuthenticationResponse register(RegistrationRequest request);
}
