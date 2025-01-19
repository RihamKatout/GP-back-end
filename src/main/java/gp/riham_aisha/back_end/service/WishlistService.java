package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;

import java.util.List;

public interface WishlistService {
    public void addProductToWishlist(Long productId);
    public void removeProductFromWishlist(Long productId);
    public void clearWishlist();
    public List<Product> getWishlist();
}
