package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.model.Product;
import gp.riham_aisha.back_end.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<Product>> getWishlist() {
        return ResponseEntity.ok(wishlistService.getWishlist());
    }

    @PostMapping("/{productId}")
    public void addProductToWishlist(@PathVariable Long productId) {
        wishlistService.addProductToWishlist(productId);
    }

    @DeleteMapping("/{productId}")
    public void removeProductFromWishlist(@PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(productId);
    }

    @DeleteMapping
    public void clearWishlist() {
        wishlistService.clearWishlist();
    }
}
