package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.repository.ProductCategoryRepository;
import gp.riham_aisha.back_end.repository.StoreCategoryRepository;
import gp.riham_aisha.back_end.service.CategoryService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final StoreCategoryRepository storeCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private static final String STORE_IS_NOT_FOUND = "Store category with id: {} is not found";
    private static final String PRODUCT_IS_NOT_FOUND = "Product category with id: {} is not found";

    /*------------------------------- Store Category -------------------------------*/
    @Override
    public StoreCategory addNewStoreCategory(String storeCategory, String imageURL) {
        StoreCategory newStoreCategory = new StoreCategory(storeCategory, imageURL);
        storeCategoryRepository.save(newStoreCategory);
        log.info("New category added: {} by: {}", newStoreCategory.getCategoryName(), AuthUtil.getCurrentUser());
        return newStoreCategory;
    }

    @Override
    public StoreCategory updateStoreCategory(Long id, String storeCategoryName) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(STORE_IS_NOT_FOUND, id))
        );
        storeCategory.setCategoryName(storeCategoryName);
        storeCategoryRepository.save(storeCategory);
        log.info("Category updated: {} by: {}", storeCategory.getCategoryName(), AuthUtil.getCurrentUser());
        return storeCategory;
    }

    @Override
    public StoreCategory getStoreCategory(Long id) {
        return storeCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(STORE_IS_NOT_FOUND, id))
        );
    }

    @Override
    public void deleteStoreCategory(Long id) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(STORE_IS_NOT_FOUND, id))
        );
        storeCategoryRepository.delete(storeCategory);
        log.info("Category deleted: {} by: {}", id, AuthUtil.getCurrentUser());
    }

    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }

    /*------------------------------ Product Category ------------------------------*/
    @Override
    public ProductCategory addNewProductCategory(ProductCategoryDTO productCategoryDTO) {
        StoreCategory storeCategory = getStoreCategory(productCategoryDTO.storeCategoryId());
        ProductCategory newProductCategory = new ProductCategory(productCategoryDTO.name(), storeCategory);
        productCategoryRepository.save(newProductCategory);
        log.info("New product category added: {} by: {}", newProductCategory.getName(), AuthUtil.getCurrentUser());
        return newProductCategory;
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        StoreCategory storeCategory = getStoreCategory(productCategoryDTO.storeCategoryId());
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(PRODUCT_IS_NOT_FOUND, id))
        );
        productCategory.setName(productCategoryDTO.name());
        productCategory.setStoreCategory(storeCategory);
        productCategoryRepository.save(productCategory);
        log.info("Product category updated: {} by: {}", productCategory.getName(), AuthUtil.getCurrentUser());
        return productCategory;
    }

    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(PRODUCT_IS_NOT_FOUND, id))
        );
        productCategoryRepository.delete(productCategory);
        log.info("Product category deleted: {} by: {}", id, AuthUtil.getCurrentUser());
    }

    @Override
    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(PRODUCT_IS_NOT_FOUND, id))
        );
    }


}
