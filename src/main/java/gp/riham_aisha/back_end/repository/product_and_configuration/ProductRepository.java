package gp.riham_aisha.back_end.repository.product_and_configuration;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByStoreId(Long id);

    @Query(value = "SELECT * FROM products WHERE stock <= stock_edge AND store_id = :storeId AND need_stock = true", nativeQuery = true)
    List<Product> findLowStockProductsByStoreNative(@Param("storeId") Long storeId);

}
