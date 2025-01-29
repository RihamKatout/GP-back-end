package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @GetMapping("")
    public ResponseEntity<List<User>> searchForUsers(@RequestParam(required = true) String search) {
        List<User> users = userService.searchForUsers(search);
        return ResponseEntity.ok().body(users);
    }
}
