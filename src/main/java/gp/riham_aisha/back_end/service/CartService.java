package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getUserCart();

    void addItemToCart(CartItem cartItem);

    CartItem updateCartItemQuantity(CartItem cartItem);

    void removeItemFromCart(Long cartItemId);

    void clearCart();
}
