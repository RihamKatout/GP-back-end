package gp.riham_aisha.back_end.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.riham_aisha.back_end.model.CartItem;
import gp.riham_aisha.back_end.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart() {
        return new ResponseEntity<>(cartService.getUserCart(), HttpStatus.OK);
    }

    @PostMapping
    public void addItemToCart(@RequestBody CartItem cartItem) {
        cartService.addItemToCart(cartItem);
    }

    @PutMapping
    public ResponseEntity<CartItem> updateItemQuantity(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartService.updateCartItemQuantity(cartItem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void removeItemFromCart(@PathVariable Long id) {
        cartService.removeItemFromCart(id);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

    @DeleteMapping("/items")
    public void removeItemsFromCart(@RequestBody Object input) {
        ObjectMapper mapper = new ObjectMapper();
        Long[] ids = mapper.convertValue(input, Long[].class);

        Arrays.asList(ids).forEach(cartService::removeItemFromCart);
    }
}
