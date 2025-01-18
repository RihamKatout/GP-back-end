package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id);
    Product addProduct(ProductDto product);
    Product updateProduct(Long id, ProductDto product);
    void deleteProduct(Long id);
    Page<Product> searchProducts(SearchProductParameters parameters, Pageable pageable);
    List<Product> lowStockProducts(Long storeId);
}
