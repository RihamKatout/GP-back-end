package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.StoreCategory;

import java.util.List;

public interface CategoryService {
    /*------------------------------- Store Category -------------------------------*/
    StoreCategory addNewStoreCategory(String storeCategory, String imageURL);

    StoreCategory updateStoreCategory(Long id, String storeCategory);

    StoreCategory getStoreCategory(Long id);

    void deleteStoreCategory(Long id);

    List<StoreCategory> getAllStoreCategories();

    /*------------------------------ Product Category ------------------------------*/
    ProductCategory addNewProductCategory(ProductCategoryDTO productCategoryDTO);

    List<ProductCategory> getAllProductCategories();

    ProductCategory updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO);

    void deleteProductCategory(Long id);

    ProductCategory getProductCategory(Long id);
}
