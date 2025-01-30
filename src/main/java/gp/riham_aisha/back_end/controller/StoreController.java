package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.dto.store.GetStoresDto;
import gp.riham_aisha.back_end.dto.store.StoreBasicInfoDto;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStore(id));
    }

    @GetMapping("")
    public List<GetStoresDto> getStoreByCategoryId(@RequestParam(required = false) Long storeCategoryId) {
        return storeService.getStoresByCategoryId(storeCategoryId);
    }

    @PostMapping("")
    public ResponseEntity<Void> createStore(@Valid @RequestBody AddStoreDto addStoreDto) {
        StoreBasicInfoDto.fromStore(storeService.addNewStore(addStoreDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('STORE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<StoreBasicInfoDto> updateStore(@PathVariable Long id, @Valid @RequestBody AddStoreDto addStoreDto) {
        StoreBasicInfoDto updatedStore = StoreBasicInfoDto.fromStore(storeService.updateStore(id, addStoreDto));
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

    @PreAuthorize("hasAnyAuthority('STORE_MANAGER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
