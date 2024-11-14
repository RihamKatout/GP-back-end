package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.model.StoreCategory;
import gp.riham_aisha.back_end.service.StoreCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/store-category")
public class StoreCategoryController {
    private final StoreCategoryService storeCategoryService;

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseEntity.ok(storeCategoryService.getAllStoreCategories());
    }

    @PostMapping
    public ResponseEntity<Object> addNewCategory(@RequestBody String newCategoryName) {
        StoreCategory newStoreCategory = storeCategoryService.addNewStoreCategory(newCategoryName);
        log.info("New category added: {}", newStoreCategory.getCategoryName());
        return ResponseEntity.ok(newStoreCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody String newCategoryName) {
        StoreCategory updatedStoreCategory = storeCategoryService.updateStoreCategory(id, newCategoryName);
        log.info("Category updated: {}", updatedStoreCategory.getCategoryName());
        return ResponseEntity.ok(updatedStoreCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        storeCategoryService.deleteStoreCategory(id);
        log.info("Category deleted: {}", id);
        return ResponseEntity.ok().build();
    }
}
