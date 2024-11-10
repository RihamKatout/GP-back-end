package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.StoreCategory;

import java.util.List;

public interface StoreCategoryService {
    StoreCategory addNewStoreCategory(String storeCategory);
    StoreCategory updateStoreCategory(Long id, String storeCategory);
    void deleteStoreCategory(Long id);
    List<StoreCategory> getAllStoreCategories();
}
