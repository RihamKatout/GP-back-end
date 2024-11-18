package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("")
    public ResponseEntity<Object> addNewAdmin(@RequestBody RegistrationRequest request) {
        User newAdmin = adminService.addNewAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAdmin);
    }

    @PostMapping("/support")
    public ResponseEntity<Object> addNewSupport(@RequestBody RegistrationRequest request) {
        User newAdmin = adminService.addNewSupport(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newAdmin);
    }

    @DeleteMapping("/support/{id}")
    public ResponseEntity<Object> deleteSupport(@PathVariable Long id) {
        adminService.deleteSupport(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Object> resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        adminService.resetPassword(id, newPassword);
        return ResponseEntity.ok().build();
    }
}
