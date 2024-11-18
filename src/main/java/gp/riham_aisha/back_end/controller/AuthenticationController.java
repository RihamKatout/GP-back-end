package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.AuthenticationResponse;
import gp.riham_aisha.back_end.dto.LoginRequest;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.util.Validator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest request, BindingResult result){
        Validator.validateBody(result);
        AuthenticationResponse response = authenticationService.register(request, Role.CUSTOMER);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", "Bearer " + response.token())
                .body(response.user());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + response.token())
                .body(response.user());
    }
}