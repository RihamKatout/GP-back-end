package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.CartItem;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.repository.CartRepository;
import gp.riham_aisha.back_end.service.CartService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CartRepository cartRepository;
    private final ProductService productService;

    private User getUser() {
        String username = AuthUtil.getCurrentUser();
        return userService.getUserByUsername(username);
    }

    private CartItem isCartItemExists(User user, CartItem cartItem) {
        return cartItem.getId() != null ?
                user.getCart().stream().filter(item ->
                        item.getId().equals(cartItem.getId())).findFirst().orElse(null) :
                user.getCart().stream().filter(item ->
                        item.getProduct().getId().equals(cartItem.getProduct().getId())
                                && item.getSize().equals(cartItem.getSize())).findFirst().orElse(null);
    }

    @Override
    public List<CartItem> getUserCart() {
        return getUser().getCart();
    }

    @Override
    public void addItemToCart(CartItem cartItem) {
        User user = getUser();
        if (isCartItemExists(user, cartItem) != null) {
            throw new IllegalArgumentException("Item already exists in the cart");
        }
        cartItem.setUser(user);
        Product product = productService.getProductById(cartItem.getProduct().getId());
        cartItem.setProduct(product);
        cartItem.setStoreId(product.getStore().getId());
        cartItem.setStoreName(product.getStore().getName());
        cartRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItemQuantity(CartItem cartItem) {
        CartItem item = isCartItemExists(getUser(), cartItem);
        if (item != null) {
            item.setQuantity(cartItem.getQuantity());
            cartRepository.save(item);
            return item;
        }
        throw new IllegalArgumentException("Item not found in the cart");
    }


    @Override
    public void removeItemFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart() {
        cartRepository.deleteAll(getUser().getCart());
    }
}
