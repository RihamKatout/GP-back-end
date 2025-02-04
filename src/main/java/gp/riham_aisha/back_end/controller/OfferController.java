package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.OfferDto;
import gp.riham_aisha.back_end.dto.OfferWithProducts;
import gp.riham_aisha.back_end.model.Offer;
import gp.riham_aisha.back_end.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/{id}")
    public ResponseEntity<OfferWithProducts> getOfferById(@PathVariable Long id){
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @GetMapping("/public")
    public ResponseEntity<List<OfferDto>> getPublicOffers(){
        return ResponseEntity.ok(offerService.getAllPublicOffers());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OfferDto>> getOffersForStore(@PathVariable Long storeId){
        return ResponseEntity.ok(offerService.getOffersForStore(storeId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @PostMapping("/public")
    public ResponseEntity<OfferDto> addPublicOffer(@RequestBody OfferDto offerDto){
        return ResponseEntity.ok(offerService.addOffer(offerDto, true));
    }

    @PreAuthorize("hasAuthority('STORE_MANAGER')")
    @PostMapping("/store")
    public ResponseEntity<OfferDto> addStoreOffer(@RequestBody OfferDto offerDto){
        return ResponseEntity.ok(offerService.addOffer(offerDto, false));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT', 'STORE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable Long id, @RequestBody OfferDto offerDto){
        return ResponseEntity.ok(offerService.updateOffer(id, offerDto));
    }
    
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT', 'STORE_MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{offerId}/products")
    public ResponseEntity<Void> addProductsToOffer(@PathVariable Long offerId, @RequestBody Long[] productIds){
        offerService.addProductsToOffer(offerId, productIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{offerId}/products")
    public ResponseEntity<Void> removeProductsFromOffer(@PathVariable Long offerId, @RequestBody Long[] productIds){
        offerService.removeProductsFromOffer(offerId, productIds);
        return ResponseEntity.ok().build();
    }
}
