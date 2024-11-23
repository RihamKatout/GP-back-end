package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
