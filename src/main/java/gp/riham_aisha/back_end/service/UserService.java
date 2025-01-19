package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    void deleteUser(Long id);

    void resetPassword(Long id, String newPassword);

    User getUserByUsername(String username);

    List<Product> getWishlist(String username);

    void addProductToWishlist(String username, Product product);

    void removeProductFromWishlist(String username, Long productId);

    void clearWishlist(String username);

    boolean isProductInWishlist(String username, Long productId);
}
