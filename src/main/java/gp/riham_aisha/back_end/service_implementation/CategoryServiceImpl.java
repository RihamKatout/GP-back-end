package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.product.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.store.StoreCategoryDTO;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.repository.ProductCategoryRepository;
import gp.riham_aisha.back_end.repository.StoreCategoryRepository;
import gp.riham_aisha.back_end.repository.StoreRepository;
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
    private final StoreRepository storeRepository;
    private static final String STORE_IS_NOT_FOUND = "Store category with id: {} is not found";
    private static final String PRODUCT_IS_NOT_FOUND = "Product category with id: {} is not found";

    /*------------------------------- Store Category -------------------------------*/
    @Override
    public StoreCategoryDTO getStoreCategoryById(Long id) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(STORE_IS_NOT_FOUND, id))
        );
        return new StoreCategoryDTO(storeCategory.getId(), storeCategory.getName(),
                storeCategory.getImageurl(), storeCategory.getProductCategories());
    }

    @Override
    public StoreCategory addNewStoreCategory(String storeCategory, String imageurl) {
        StoreCategory newStoreCategory = new StoreCategory(storeCategory, imageurl);
        storeCategoryRepository.save(newStoreCategory);
        log.info("New category added: {} by: {}", newStoreCategory.getName(), AuthUtil.getCurrentUser());
        return newStoreCategory;
    }

    @Override
    public StoreCategory updateStoreCategory(Long id, String storeCategoryName, String imageurl) {
        if(id == 1){
            throw new SecurityException("Cannot update default category");
        }
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(STORE_IS_NOT_FOUND, id))
        );
        storeCategory.setName(storeCategoryName);
        storeCategory.setImageurl(imageurl);
        storeCategoryRepository.save(storeCategory);
        log.info("Category updated: {} by: {}", storeCategory.getName(), AuthUtil.getCurrentUser());
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
        if (storeCategory.getId() == 1){
            throw new SecurityException("Cannot delete default category");
        }
        StoreCategory defaultCategory = storeCategoryRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default StoreCategory not found"));
        // Reassign product categories to the default category
        storeCategory.getProductCategories().forEach(productCategory -> productCategory.setStoreCategory(defaultCategory));
        productCategoryRepository.saveAll(storeCategory.getProductCategories());

        // Reassign all stores to the default category
        storeCategory.getStores().forEach(store -> store.setStoreCategory(defaultCategory));
        storeRepository.saveAll(storeCategory.getStores());
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
        ProductCategory newProductCategory = new ProductCategory(productCategoryDTO.name(), storeCategory, productCategoryDTO.imageurl());
        productCategoryRepository.save(newProductCategory);
        log.info("New product category added: {} by: {}", newProductCategory.getName(), AuthUtil.getCurrentUser());
        return newProductCategory;
    }

    @Override
    public List<ProductCategory> getAllProductCategoriesForStoreCategory(Long storeCategoryId) {
        return productCategoryRepository.findByStoreCategory_Id(storeCategoryId);
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
