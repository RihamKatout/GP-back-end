package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.StoreDto;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStore(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @PostMapping("")
    public ResponseEntity<Store> createStore(@Valid @RequestBody StoreDto storeDto) {
        Store store = storeService.addNewStore(storeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(store);
    }

    @PreAuthorize("hasAuthority('STORE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @Valid @RequestBody StoreDto storeDto) {
        Store updatedStore = storeService.updateStore(id, storeDto);
        return ResponseEntity.ok(updatedStore);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @PutMapping("/{id}/ban")
    public void banStore(@PathVariable Long id) {
        storeService.banStore(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT', 'STORE_MANAGER')")
    @PutMapping("/{id}/activate")
    public void activateStore(@PathVariable Long id) {
        storeService.activateStore(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @PutMapping("/{id}/unban")
    public void unbanStore(@PathVariable Long id) {
        storeService.unbanStore(id);
    }

    @PreAuthorize("hasAuthority('STORE_MANAGER')")
    @PutMapping("/{id}/deactivate")
    public void deactivateStore(@PathVariable Long id) {
        storeService.deactivateStore(id);
    }

    @PreAuthorize("hasAuthority('STORE_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
