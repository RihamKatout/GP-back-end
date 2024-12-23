package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByStoreCategory_Id(Long storeCategoryId);
}
