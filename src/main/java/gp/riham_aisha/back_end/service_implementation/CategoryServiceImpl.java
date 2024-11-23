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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final StoreCategoryRepository storeCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;

    /*------------------------------- Store Category -------------------------------*/
    @Override
    public StoreCategory addNewStoreCategory(String storeCategory) {
        StoreCategory newStoreCategory = new StoreCategory(storeCategory);
        storeCategoryRepository.save(newStoreCategory);
        log.info("New category added: {} by: {}", newStoreCategory.getCategoryName(), AuthUtil.getCurrentUser());
        return newStoreCategory;
    }

    @Override
    public StoreCategory updateStoreCategory(Long id, String storeCategoryName) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow();
        storeCategory.setCategoryName(storeCategoryName);
        storeCategoryRepository.save(storeCategory);
        log.info("Category updated: {} by: {}", storeCategory.getCategoryName(), AuthUtil.getCurrentUser());
        return storeCategory;
    }

    @Override
    public Optional<StoreCategory> getStoreCategory(Long id) {
        return storeCategoryRepository.findById(id);
    }

    @Override
    public void deleteStoreCategory(Long id) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow();
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
        StoreCategory storeCategory = getStoreCategory(productCategoryDTO.storeCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Store category with id: " + productCategoryDTO.storeCategoryId() + " is not found"));
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
        StoreCategory storeCategory = getStoreCategory(productCategoryDTO.storeCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Store category with id: " + productCategoryDTO.storeCategoryId() + " is not found"));
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow();
        productCategory.setName(productCategoryDTO.name());
        productCategory.setStoreCategory(storeCategory);
        productCategoryRepository.save(productCategory);
        log.info("Product category updated: {} by: {}", productCategory.getName(), AuthUtil.getCurrentUser());
        return productCategory;
    }

    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow();
        productCategoryRepository.delete(productCategory);
        log.info("Product category deleted: {} by: {}", id, AuthUtil.getCurrentUser());
    }


}
