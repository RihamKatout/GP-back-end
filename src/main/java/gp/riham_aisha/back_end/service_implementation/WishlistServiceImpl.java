package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.Product;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.service.WishlistService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final UserService userService;
    private final ProductService productService;

    @Override
    public void addProductToWishlist(Long productId) {
        userService.addProductToWishlist(AuthUtil.getCurrentUser(), productService.getProductById(productId));
    }

    @Override
    public void removeProductFromWishlist(Long productId) {
        userService.removeProductFromWishlist(AuthUtil.getCurrentUser(), productId);
    }

    @Override
    public void clearWishlist() {
        userService.clearWishlist(AuthUtil.getCurrentUser());
    }

    @Override
    public List<Product> getWishlist() {
        return userService.getWishlist(AuthUtil.getCurrentUser());
    }
}
