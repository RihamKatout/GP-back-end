package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.repository.StoreCategoryRepository;
import gp.riham_aisha.back_end.service.StoreCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreCategoryServiceImpl implements StoreCategoryService {
    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    public StoreCategory addNewStoreCategory(String storeCategory) {
        StoreCategory newStoreCategory = new StoreCategory(storeCategory);
        storeCategoryRepository.save(newStoreCategory);
        log.info("New category added: {}", newStoreCategory.getCategoryName());
        return newStoreCategory;
    }

    @Override
    public StoreCategory updateStoreCategory(Long id, String storeCategoryName) {
        StoreCategory storeCategory = storeCategoryRepository.findById(id).orElseThrow();
        storeCategory.setCategoryName(storeCategoryName);
        storeCategoryRepository.save(storeCategory);
        log.info("Category updated: {}", storeCategory.getCategoryName());
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
        log.info("Category deleted: {}", id);
    }

    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryRepository.findAll();
    }
}
