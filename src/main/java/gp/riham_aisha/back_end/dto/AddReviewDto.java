package gp.riham_aisha.back_end.dto;

public record AddReviewDto(
        String feedback,
        Integer rating,
        Boolean isProductReview,
        Long productId,
        Long storeId
) {
}
