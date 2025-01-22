package gp.riham_aisha.back_end.controller;


import gp.riham_aisha.back_end.dto.store_manager.GetStoresDto;
import gp.riham_aisha.back_end.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ManagerService storeManagerService;

    @GetMapping("/stores")
    public ResponseEntity<List<GetStoresDto>> getAllStoresForManager() {
        return ResponseEntity.ok().body(storeManagerService.getAllStoresForManager());
    }

    @GetMapping("/{id}/analytics")
    public ResponseEntity<Object> getStoreAnalytics(@PathVariable Long id) {
        return ResponseEntity.ok().body(storeManagerService.getStoreAnalytics(id));
    }
}
