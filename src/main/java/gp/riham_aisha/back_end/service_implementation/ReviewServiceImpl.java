package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.AddReviewDto;
import gp.riham_aisha.back_end.model.Review;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.repository.ReviewRepository;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.ReviewService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;

    @Override
    public List<Review> getReviewsForProduct(Long productId) {
        return reviewRepository.findByProduct_Id(productId);
    }

    @Override
    public List<Review> getReviewsForStore(Long storeId) {
        return reviewRepository.findByStore_Id(storeId);
    }

    @Transactional
    @Override
    public void addReview(AddReviewDto reviewDto) {
        User currentUser;
        try {
            currentUser =
                    userService.getUserByUsername(AuthUtil.getCurrentUser());
        } catch (Exception e) {
            currentUser = userService.getUser(1L);
        }
        Boolean isProductReview = reviewDto.isProductReview();
        Review review = new Review(reviewDto.feedback(), reviewDto.rating(), isProductReview, currentUser);
        if (Boolean.TRUE.equals(isProductReview)) {
            addReviewForProduct(reviewDto.productId(), review);
        } else {
            addReviewForStore(reviewDto.storeId(), review);
        }
    }

    public void addReviewForProduct(Long productId, Review review) {
        Product product = productService.getProductById(productId);
        product.addNewReview(review);
        review.setProduct(product);
        reviewRepository.save(review);
    }

    public void addReviewForStore(Long storeId, Review review) {
        Store store = storeService.getStore(storeId);
        store.addReview(review);
        review.setStore(store);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
