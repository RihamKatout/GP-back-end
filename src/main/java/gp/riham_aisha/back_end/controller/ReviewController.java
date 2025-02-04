package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.AddReviewDto;
import gp.riham_aisha.back_end.model.Review;
import gp.riham_aisha.back_end.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsForProduct(id));
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<Review>> getReviewsByStoreId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsForStore(id));
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody AddReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPPORT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

}
