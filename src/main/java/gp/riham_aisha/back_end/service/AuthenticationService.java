package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dta.AuthenticationResponse;
import gp.riham_aisha.back_end.dta.LoginRequest;
import gp.riham_aisha.back_end.dta.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    AuthenticationResponse authenticate(LoginRequest request) throws AuthenticationException;

    @Transactional
    AuthenticationResponse register(RegistrationRequest request, Role... roles);
}
