package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.AddReviewDto;
import gp.riham_aisha.back_end.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsForProduct(Long productId);
    List<Review> getReviewsForStore(Long storeId);
    void addReview(AddReviewDto reviewDto);
    void deleteReview(Long reviewId);
}
