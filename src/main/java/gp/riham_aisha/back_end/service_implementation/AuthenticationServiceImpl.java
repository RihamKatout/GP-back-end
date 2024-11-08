package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dta.AuthenticationResponse;
import gp.riham_aisha.back_end.dta.LoginRequest;
import gp.riham_aisha.back_end.dta.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.repository.UserRepository;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse authenticate(LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isEmpty()) {
            String msg = "User with email: " + request.email() + " is not found";
            log.info(msg);
            throw new EntityNotFoundException(msg);
        }
        log.info("User with email " + request.email() + " is found");
        String jwtToken = jwtService.generateToken(user.get());
        return new AuthenticationResponse(jwtToken, user.get());
    }

    @Transactional
    @Override
    public AuthenticationResponse register(RegistrationRequest request, Role... roles) {
        User user = new User(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        log.info("UserServiceImpl --> registerUser --> token was generated for username: " + user.getUsername());
        return new AuthenticationResponse(jwtToken, user);

    }
}