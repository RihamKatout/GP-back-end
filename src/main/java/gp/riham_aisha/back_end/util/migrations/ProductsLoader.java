package gp.riham_aisha.back_end.util.migrations;

import gp.riham_aisha.back_end.service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductsLoader {
    private final ProductService productService;

    public void loadProducts() {
        new SweetTouchesProducts(productService).loadProducts();
    }

}
