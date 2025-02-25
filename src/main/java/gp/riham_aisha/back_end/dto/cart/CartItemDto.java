package gp.riham_aisha.back_end.dto.cart;

import gp.riham_aisha.back_end.model.cart.CartItem;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;

import java.util.List;

public record CartItemDto(CartItem cartItem, List<ProductConfiguration> configurations) {
}
