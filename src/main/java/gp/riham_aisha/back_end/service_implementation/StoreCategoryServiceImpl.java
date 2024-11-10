package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.repository.StoreCategoryRepository;
import gp.riham_aisha.back_end.service.StoreCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCategoryServiceImpl implements StoreCategoryService {
    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public StoreCategory addNewStoreCategory(String storeCategory) {
        return storeCategoryRepository.save(new StoreCategory(storeCategory));
    }

    @Override
    public StoreCategory updateStoreCategory(Long id, String storeCategoryName) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow();
        storeCategory.setCategoryName(storeCategoryName);
        return storeCategoryRepository.save(storeCategory);
    }

    @Override
    public void deleteStoreCategory(Long id) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow();
        storeCategoryRepository.delete(storeCategory);
    }

    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }
}
