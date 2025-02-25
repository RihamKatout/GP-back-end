package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStore_Id(Long id);
    List<Review> findByProduct_Id(Long id);
}
