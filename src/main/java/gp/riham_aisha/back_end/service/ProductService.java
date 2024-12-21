package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    public Product getProductById(Long id);
    public Product addProduct(ProductDto product);
    public Product updateProduct(Long id, ProductDto product);
    public void deleteProduct(Long id);
    public Page<Product> searchProducts(SearchProductParameters parameters, Pageable pageable);
}
