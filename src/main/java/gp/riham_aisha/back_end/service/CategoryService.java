package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.product.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.store.StoreCategoryDTO;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.StoreCategory;

import java.util.List;

public interface CategoryService {
    /*------------------------------- Store Category -------------------------------*/
    StoreCategoryDTO getStoreCategoryById(Long id);

    StoreCategory addNewStoreCategory(String storeCategory, String imageurl);

    StoreCategory updateStoreCategory(Long id, String storeCategory, String imageurl);

    StoreCategory getStoreCategory(Long id);

    void deleteStoreCategory(Long id);

    List<StoreCategory> getAllStoreCategories();

    /*------------------------------ Product Category ------------------------------*/
    ProductCategory addNewProductCategory(ProductCategoryDTO productCategoryDTO);

    List<ProductCategory> getAllProductCategoriesForStoreCategory(Long storeCategoryId);

    ProductCategory updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO);

    void deleteProductCategory(Long id);

    ProductCategory getProductCategory(Long id);
}
