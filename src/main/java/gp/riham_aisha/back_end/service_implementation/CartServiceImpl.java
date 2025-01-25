package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.cart.CartItemDto;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.model.cart.CartItem;
import gp.riham_aisha.back_end.model.cart.ConfigurationInstance;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.repository.CartRepository;
import gp.riham_aisha.back_end.service.CartService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
                        item.getId().equals(cartItem.getId())).findFirst().orElseThrow(
                        () -> new EntityNotFoundException("Item not found in the cart")) : null;
    }

    @Override
    public List<CartItemDto> getUserCart() {
        List<CartItem> cartItems = getUser().getCart();
        return cartItems.stream().map(cartItem ->
                new CartItemDto(cartItem, cartItem.getProduct().getConfigurations())
        ).toList();
    }

    @Override
    @Transactional
    public void addItemToCart(CartItem cartItem) {
        // Retrieve the authenticated user
        User user = getUser();
        cartItem.setUser(user);

        // Fetch the product and set its details to the cart item
        Product product = productService.getProductById(cartItem.getProduct().getId());
        cartItem.setProduct(product);
        cartItem.setStoreId(product.getStore().getId());
        cartItem.setStoreName(product.getStore().getName());

        // Process the configuration instances before saving the cart item
        List<ConfigurationInstance> configurationInstances = cartItem.getConfigurationInstances();
        if (configurationInstances != null && !configurationInstances.isEmpty()) {
            configurationInstances.forEach(configurationInstance -> {
                // Link each configuration instance to the cart item
                configurationInstance.setCartItem(cartItem);
            });
        }

        // Save the cart item and cascade persist the configuration instances
        cartRepository.save(cartItem);

        // Note: If cascade type is not configured for ConfigurationInstance,
        // you may need to save configuration instances explicitly in their repository.
    }


    @Override
    @Transactional
    public CartItem updateCartItemQuantity(CartItem cartItem) {
        CartItem item = cartRepository.findById(cartItem.getId()).orElseThrow(
                () -> new EntityNotFoundException("Item not found in the cart"));
        item.setQuantity(cartItem.getQuantity());
        return cartRepository.save(item);
    }

    @Override
    public CartItemDto updateCartItem(CartItem cartItem) {
        CartItem item = isCartItemExists(getUser(), cartItem);
        if (item != null) {
            item.setQuantity(cartItem.getQuantity());
            item.setDetails(cartItem.getDetails());
            item.setMessage(cartItem.getMessage());
            item.setConfigurationInstances(cartItem.getConfigurationInstances());
            cartRepository.save(item);
            return new CartItemDto(item, item.getProduct().getConfigurations());
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
