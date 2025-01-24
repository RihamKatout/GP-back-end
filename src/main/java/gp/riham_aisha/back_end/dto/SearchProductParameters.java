package gp.riham_aisha.back_end.dto;

public record SearchProductParameters(String keyWord, Long categoryId, Long storeId, Long storeCategoryId,
                                      Boolean isAvailable, Boolean threeDModel, Boolean is3dCustomizable, Double minPrice,
                                      Double maxPrice, Double minRating, Long id, Boolean customizable) {
}
