package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.cart.CartItemDto;
import gp.riham_aisha.back_end.model.cart.CartItem;

import java.util.List;

public interface CartService {
    List<CartItemDto> getUserCart();

    void addItemToCart(CartItem cartItem);

    CartItem updateCartItemQuantity(CartItem cartItem);

    CartItemDto updateCartItem(CartItem cartItem);

    void removeItemFromCart(Long cartItemId);

    void clearCart();
}
