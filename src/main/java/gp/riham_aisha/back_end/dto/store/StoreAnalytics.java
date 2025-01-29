package gp.riham_aisha.back_end.dto.store;

import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;

import java.util.List;
import java.util.Set;

public record StoreAnalytics(
        String storeName, Long storeCategoryId, List<Product> lowStock, Set<ProductCategory> productCategories) {
}
