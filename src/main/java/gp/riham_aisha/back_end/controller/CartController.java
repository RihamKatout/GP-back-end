package gp.riham_aisha.back_end.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.riham_aisha.back_end.dto.cart.CartItemDto;
import gp.riham_aisha.back_end.model.cart.CartItem;
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
    public ResponseEntity<List<CartItemDto>> getCart() {
        return new ResponseEntity<>(cartService.getUserCart(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addItemToCart(@RequestBody CartItem cartItem) {
        cartService.addItemToCart(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<CartItem> updateItemQuantity(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartService.updateCartItemQuantity(cartItem), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        cartItem.setId(id);
        return new ResponseEntity<>(cartService.updateCartItem(cartItem), HttpStatus.OK);
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
