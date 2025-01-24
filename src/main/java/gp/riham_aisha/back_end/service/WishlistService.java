package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;

import java.util.List;

public interface WishlistService {
    void addProductToWishlist(Long productId);
    void removeProductFromWishlist(Long productId);
    void clearWishlist();
    List<Product> getWishlist();
}
