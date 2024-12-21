package gp.riham_aisha.back_end.dto;

public record SearchProductParameters (String keyWord, Long categoryId, Long storeId, Long storeCategoryId,
                                       Boolean isAvailable, Double minPrice, Double maxPrice, Double minRating, Long id) {
}
