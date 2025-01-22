package gp.riham_aisha.back_end.dto.product;


import gp.riham_aisha.back_end.dto.store.StoreBasicInfoDto;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;

import java.util.List;

//  ------------------------------------------------------------------
// | This Dto is used to represent a product with its all information |
// | ,configurations, and reviews                                     |
// | mainly used for product details page in customer side            |
//  ------------------------------------------------------------------
// TODO: add lists of reviews
public record ProductDetailsDto(Product product, StoreBasicInfoDto store, Boolean inWishlist, List<ProductConfiguration> configurations) {

    public static ProductDetailsDto fromProductInfo(Product product, Boolean inWishlist) {
        return new ProductDetailsDto(
                product,
                StoreBasicInfoDto.fromStore(product.getStore()),
                inWishlist,
                product.getConfigurations()
        );
    }
}
