package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.StoreCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    /*------------------------------- Store Category -------------------------------*/
    StoreCategory addNewStoreCategory(String storeCategory);

    StoreCategory updateStoreCategory(Long id, String storeCategory);

    Optional<StoreCategory> getStoreCategory(Long id);

    void deleteStoreCategory(Long id);

    List<StoreCategory> getAllStoreCategories();

    /*------------------------------ Product Category ------------------------------*/
    ProductCategory addNewProductCategory(ProductCategoryDTO productCategoryDTO);

    List<ProductCategory> getAllProductCategories();

    ProductCategory updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO);

    void deleteProductCategory(Long id);

}
