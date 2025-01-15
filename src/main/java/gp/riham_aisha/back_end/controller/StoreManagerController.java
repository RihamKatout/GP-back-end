package gp.riham_aisha.back_end.controller;


import gp.riham_aisha.back_end.dto.store_manager.ManagerStoresDto;
import gp.riham_aisha.back_end.service.StoreManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-manager")
public class StoreManagerController {
    private final StoreManagerService storeManagerService;

    @GetMapping
    public ResponseEntity<List<ManagerStoresDto>> getAllStoresForManager() {
        return ResponseEntity.ok().body(storeManagerService.getAllStoresForManager());
    }
}
