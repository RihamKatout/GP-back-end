package gp.riham_aisha.back_end.dto.product;

public record SearchProductParameters(String keyWord, Long categoryId, Long storeId, Long storeCategoryId,
                                      Boolean isAvailable, Boolean threeDModel, Boolean is3dCustomizable, Double minPrice,
                                      Double maxPrice, Double minRating, Long id, Boolean customizable, Long offerId) {
}
