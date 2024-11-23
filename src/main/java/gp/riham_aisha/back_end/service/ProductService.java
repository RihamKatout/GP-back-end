package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProductsForStore(Long id);
    public List<Product> getAllProductsForCategory(Long id);
    public Product getProductById(Long id);
    public Product addProduct(ProductDto product);
    public Product updateProduct(Long id, ProductDto product);
    public void deleteProduct(Long id);
}
