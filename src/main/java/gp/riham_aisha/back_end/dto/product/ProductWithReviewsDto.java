package gp.riham_aisha.back_end.dto.product;


import gp.riham_aisha.back_end.model.product_and_configuration.Product;

//  ------------------------------------------------------------------
// | This Dto is used to represent a product with its all information |
// | mainly used for product details page in customer side            |
//  ------------------------------------------------------------------
// TODO: add lists of reviews and features
public record ProductWithReviewsDto(ProductWithStoreDto productWithStore) {
    public static ProductWithReviewsDto fromProductInfo(Product product, Boolean inWishlist) {
        return new ProductWithReviewsDto(
                ProductWithStoreDto.fromProductAndWishlist(product, inWishlist)
        );
    }
}
