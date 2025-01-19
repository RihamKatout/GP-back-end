package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.dto.product.ProductWithConfigurationsDto;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.product_and_configuration.Configuration;
import gp.riham_aisha.back_end.model.product_and_configuration.ConfigurationAttributes;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.repository.product_and_configuration.ConfigurationAttributesRepository;
import gp.riham_aisha.back_end.repository.product_and_configuration.ConfigurationRepository;
import gp.riham_aisha.back_end.repository.product_and_configuration.ProductRepository;
import gp.riham_aisha.back_end.service.CategoryService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.service.specification.ProductSpecification;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.transaction.Transactional;
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
    private final ConfigurationRepository configurationRepository;
    private final ConfigurationAttributesRepository configurationAttributesRepository;

    private final StoreService storeService;
    private final CategoryService categoryService;
    private final UserService userService;

    private boolean isProductInWishlist(Long productId) {
        if (AuthUtil.getCurrentUser().equals("System")) return false;
        return userService.isProductInWishlist(AuthUtil.getCurrentUser(), productId);
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product with id: " + id + " not found")
        );
//        product.setInWishlist(isProductInWishlist(id));
        return product;
    }


    @Override
    @Transactional
    public Product addProduct(ProductWithConfigurationsDto productDto) {
        System.out.println(productDto);
        // we need:
        // 1- store
        // 2- category
        // 3- product info
        // 4- configurations

        // 1- validate store owner
        Store store = storeService.getStore(productDto.storeId());
        AuthUtil.validateStoreOwner(store);

        // 2- validate category
        ProductCategory category = categoryService.getProductCategory(productDto.categoryId());

        // 3- create product
        Product product = new Product(productDto, store, category);
        productRepository.save(product);

        // Add configurations
        productDto.configurations().forEach(configDto -> {
            Configuration configuration = new Configuration();
            configuration.setName(configDto.getName());
            configuration.setAllowsMultipleUnits(configDto.getAllowsMultipleUnits());
            configuration.setUnitPriceImpact(configDto.getUnitPriceImpact());
            configuration.setProduct(product);
            configurationRepository.save(configuration);

            // Add configuration attributes
            configDto.getConfigurationAttributes().forEach(attrDto -> {
                ConfigurationAttributes attribute = new ConfigurationAttributes();
                attribute.setName(attrDto.getName());
                attribute.setType(attrDto.getType());
                attribute.setChoices(attrDto.getChoices());
                attribute.setConfiguration(configuration);
                configurationAttributesRepository.save(attribute);
            });
        });

        log.info("Product with id: {} was added by: {}", product.getId(), AuthUtil.getCurrentUser());
        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductWithConfigurationsDto productDto) {
        Store store = storeService.getStore(productDto.storeId());
        AuthUtil.validateStoreOwner(store);
        ProductCategory category = categoryService.getProductCategory(productDto.product().getCategoryId());
        Product newProduct = getProductById(id);
        newProduct.update(productDto, category);
        productRepository.save(newProduct);
        log.info("Product with id: {} was updated by: {}", newProduct.getId(), AuthUtil.getCurrentUser());
        return newProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        // TODO: delete features
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
                .and(ProductSpecification.has3DModel(parameters.threeDModel()))
                .and(ProductSpecification.isCustomizable(parameters.customizable()))
                .and(ProductSpecification.hasMinPrice(parameters.minPrice()))
                .and(ProductSpecification.hasMaxPrice(parameters.maxPrice()))
                .and(ProductSpecification.hasMinRating(parameters.minRating()))
                .and(ProductSpecification.hasId(parameters.id()));
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public List<Product> lowStockProducts(Long storeId) {
        return productRepository.findLowStockProductsByStoreNative(storeId);
    }

}
