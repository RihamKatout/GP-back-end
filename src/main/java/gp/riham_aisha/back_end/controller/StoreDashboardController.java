package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.service.StoreManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-dashboard")
public class StoreDashboardController {
    private final StoreManagementService storeManagementService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStoreAnalytics(@PathVariable Long id) {
        return ResponseEntity.ok().body(storeManagementService.getStoreAnalytics(id));
    }
}
