package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.model.Product;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.repository.ProductRepository;
import gp.riham_aisha.back_end.service.CategoryService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.specification.ProductSpecification;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final CategoryService categoryService;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product with id: " + id + " not found")
        );
    }

    @Override
    public Product addProduct(ProductDto product) {
        Store store = storeService.getStore(product.storeId());
        AuthUtil.validateStoreOwner(store);
        ProductCategory category = categoryService.getProductCategory(product.productCategoryId());
        Product newProduct = new Product(product, store, category);
        productRepository.save(newProduct);
        log.info("Product with id: {} was added by: {}", newProduct.getId(), AuthUtil.getCurrentUser());
        return newProduct;
    }

    @Override
    public Product updateProduct(Long id, ProductDto product) {
        Store store = storeService.getStore(product.storeId());
        AuthUtil.validateStoreOwner(store);
        ProductCategory category = categoryService.getProductCategory(product.productCategoryId());
        Product newProduct = getProductById(id);
        newProduct.update(product, store, category);
        productRepository.save(newProduct);
        log.info("Product with id: {} was updated by: {}", newProduct.getId(), AuthUtil.getCurrentUser());
        return newProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        AuthUtil.validateStoreOwner(product.getStore());
        productRepository.delete(product);
        log.info("Product with id: {} was deleted by: {}", product.getId(), AuthUtil.getCurrentUser());
    }

    @Override
    public Page<Product> searchProducts(SearchProductParameters parameters, Pageable pageable) {
        Specification<Product> specification = Specification.where(ProductSpecification.hasKeyWord(parameters.keyWord()))
                .and(ProductSpecification.hasCategory(parameters.categoryId()))
                .and(ProductSpecification.hasStore(parameters.storeId()))
                .and(ProductSpecification.hasStoreCategory(parameters.storeCategoryId()))
                .and(ProductSpecification.isAvailable(parameters.isAvailable()))
                .and(ProductSpecification.hasMinPrice(parameters.minPrice()))
                .and(ProductSpecification.hasMaxPrice(parameters.maxPrice()))
                .and(ProductSpecification.hasMinRating(parameters.minRating()))
                .and(ProductSpecification.hasId(parameters.id()));
        return productRepository.findAll(specification, pageable);
    }

}
