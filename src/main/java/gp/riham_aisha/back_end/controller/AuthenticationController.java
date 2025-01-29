package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.auth.AuthenticationResponse;
import gp.riham_aisha.back_end.dto.auth.LoginRequest;
import gp.riham_aisha.back_end.dto.auth.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.util.Validator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegistrationRequest request, BindingResult result) {
        Validator.validateBody(result);
        AuthenticationResponse response = authenticationService.register(request);
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