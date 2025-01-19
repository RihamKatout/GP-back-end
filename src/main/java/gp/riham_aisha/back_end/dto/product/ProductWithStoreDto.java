package gp.riham_aisha.back_end.dto.product;

import gp.riham_aisha.back_end.dto.store.StoreBasicInfo;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;

//  --------------------------------------------------------------------
// | This Dto is used to represent a product with its store information |
// | mainly used for listing products                                   |
//  --------------------------------------------------------------------
public record ProductWithStoreDto(Product product, StoreBasicInfo storeBasicInfo, Boolean inWishlist) {
    public static ProductWithStoreDto fromProductAndWishlist(Product product, Boolean inWishlist) {
        return new ProductWithStoreDto(product,
                StoreBasicInfo.fromStore(product.getStore()),
                inWishlist);
    }
}
