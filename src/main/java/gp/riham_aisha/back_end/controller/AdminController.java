package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("")
    public ResponseEntity<Object> addNewAdmin(@RequestBody RegistrationRequest request) {
        User newAdmin = adminService.addNewAdmin(request);
        log.info("Adding a new admin with username: " + newAdmin.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAdmin);
    }

    @PostMapping("/support")
    public ResponseEntity<Object> addNewSupport(@RequestBody RegistrationRequest request) {
        User newAdmin = adminService.addNewSupport(request);
        log.info("Adding a new support with username: " + newAdmin.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAdmin);
    }

    @DeleteMapping("/support/{id}")
    public ResponseEntity<Object> deleteSupport(@PathVariable Long id) {
        adminService.deleteSupport(id);
        log.info("Deleting support with id: " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Object> resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        adminService.resetPassword(id, newPassword);
        log.info("Resetting password for user with id: " + id);
        return ResponseEntity.ok().build();
    }
}
