package gp.riham_aisha.back_end.dto.store_manager;

import gp.riham_aisha.back_end.model.Product;

import java.util.List;

public record StoreAnalytics(
        String storeName, Long storeCategoryId, List<Product> lowStock) {
}
