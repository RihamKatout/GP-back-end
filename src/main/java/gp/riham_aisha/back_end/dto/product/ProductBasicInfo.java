package gp.riham_aisha.back_end.dto.product;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;

public record ProductBasicInfo(Long id,
                               String name,
                               String mainImageURL,
                               Boolean isAvailable) {

    public static ProductBasicInfo fromProduct(Product product) {
        return new ProductBasicInfo(product.getId(),
                                    product.getName(),
                                    product.getMainImageURL(),
                                    product.getIsAvailable());
    }
}
