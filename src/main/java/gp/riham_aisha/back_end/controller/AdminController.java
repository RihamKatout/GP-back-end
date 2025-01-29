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

    @GetMapping("/stores")
    public ResponseEntity<Object> getStores() {
        return ResponseEntity.ok(adminService.getStores());
    }

    @GetMapping("")
    public ResponseEntity<Object> getAdminsAndSupports() {
        return ResponseEntity.ok(adminService.getAdminsAndSupports());
    }
    @PostMapping("")
    public ResponseEntity<Object> addNewAdmin(@RequestBody Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.addNewAdmin(userId));
    }

    @PostMapping("/support")
    public ResponseEntity<Object> addNewSupport(@RequestBody Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.addNewSupport(userId));
    }

    @DeleteMapping("/{id}")
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
