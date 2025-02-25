package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.admin.EmailDto;
import gp.riham_aisha.back_end.dto.auth.RegistrationRequest;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final EmailService emailService;

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

    @PostMapping("/send-email")
    public ResponseEntity<Object> sendEmail(@Valid @RequestBody EmailDto emailDto) {
        try{
            emailService.sendEmail(emailDto);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
        return ResponseEntity.ok().build();
    }
}
