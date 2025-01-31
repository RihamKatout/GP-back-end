package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.cart.CartItemDto;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.model.cart.CartItem;
import gp.riham_aisha.back_end.model.cart.ConfigurationInstance;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.repository.CartRepository;
import gp.riham_aisha.back_end.repository.ConfigurationInstanceRepository;
import gp.riham_aisha.back_end.service.CartService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ConfigurationInstanceRepository configurationInstanceRepository;
    private static final String CART_ITEM_NOT_FOUND = "Item not found in the cart";

    private User getUser() {
        String username = AuthUtil.getCurrentUser();
        return userService.getUserByUsername(username);
    }

    private CartItem isCartItemExists(User user, CartItem cartItem) {
        return cartItem.getId() != null ?
                user.getCart().stream().filter(item ->
                        item.getId().equals(cartItem.getId())).findFirst().orElseThrow(
                        () -> new EntityNotFoundException(CART_ITEM_NOT_FOUND)) : null;
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
        User user = getUser();
        cartItem.setUser(user);

        Product product = productService.getProductById(cartItem.getProduct().getId());
        cartItem.setProduct(product);
        cartItem.setStoreId(product.getStore().getId());
        cartItem.setStoreName(product.getStore().getName());

        List<ConfigurationInstance> configurationInstances = cartItem.getConfigurationInstances();
        if (configurationInstances != null && !configurationInstances.isEmpty()) {
            configurationInstances.forEach(configurationInstance ->
                    configurationInstance.setCartItem(cartItem)
            );
        }
        cartRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem updateCartItemQuantity(CartItem cartItem) {
        CartItem item = cartRepository.findById(cartItem.getId()).orElseThrow(
                () -> new EntityNotFoundException(CART_ITEM_NOT_FOUND));
        item.setQuantity(cartItem.getQuantity());
        return cartRepository.save(item);
    }

    @Transactional
    @Override
    public CartItemDto updateCartItem(CartItem cartItem) {
        CartItem item = isCartItemExists(getUser(), cartItem);
        if (item != null) {
            item.setQuantity(cartItem.getQuantity());
            item.setDetails(cartItem.getDetails());
            item.setMessage(cartItem.getMessage());
            // delete all item's configuration instances that are not existed in cartItem
            item.getConfigurationInstances().removeIf(instance ->
                    cartItem.getConfigurationInstances().stream().noneMatch(
                            newConfigurationInstance -> newConfigurationInstance.getId().equals(instance.getId())
                    )
            );

            // update existed configuration instances
            cartItem.getConfigurationInstances().forEach((instance) ->
                    {
                        ConfigurationInstance configurationInstance = configurationInstanceRepository.findById(instance.getId()).orElseThrow(
                                () -> new EntityNotFoundException("Configuration instance not found"));
                        configurationInstance.setChoices(instance.getChoices());
                        configurationInstanceRepository.save(configurationInstance);
                    }
            );
            cartRepository.save(item);
            return new CartItemDto(item, item.getProduct().getConfigurations());
        }
        throw new EntityNotFoundException(CART_ITEM_NOT_FOUND);
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
