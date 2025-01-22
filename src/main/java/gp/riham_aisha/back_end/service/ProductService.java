package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.dto.product.ProductDetailsDto;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    public Product getProductById(Long id);

    ProductDetailsDto getProductDetailsById(Long id);

    Long addProduct(ProductManagementDto product);

    ProductManagementDto updateProduct(Long id, ProductManagementDto product);

    void deleteProduct(Long id);

    Page<ProductWithStoreDto> searchProducts(SearchProductParameters parameters, Pageable pageable);

    List<Product> lowStockProducts(Long storeId);
}
