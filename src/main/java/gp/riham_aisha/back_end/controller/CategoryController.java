package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService storeCategoryService;

    /*------------------------------- Store Category -------------------------------*/
    @GetMapping("/store")
    public ResponseEntity<Object> getAllStoreCategories() {
        return ResponseEntity.ok(storeCategoryService.getAllStoreCategories());
    }

    @PostMapping("/store")
    public ResponseEntity<Object> addNewStoreCategory(@RequestBody String newCategoryName, @RequestBody String imageURL) {
        return ResponseEntity.ok(storeCategoryService.addNewStoreCategory(newCategoryName, imageURL));
    }

    @PutMapping("/store/{id}")
    public ResponseEntity<Object> updateStoreCategory(@PathVariable Long id, @RequestBody String newCategoryName) {
        return ResponseEntity.ok(storeCategoryService.updateStoreCategory(id, newCategoryName));
    }

    @DeleteMapping("/store/{id}")
    public ResponseEntity<Object> deleteStoreCategory(@PathVariable Long id) {
        storeCategoryService.deleteStoreCategory(id);
        return ResponseEntity.ok().build();
    }

    /*------------------------------- Product Category -------------------------------*/
    @GetMapping("/product")
    public ResponseEntity<Object> getAllProductCategories() {
        return ResponseEntity.ok(storeCategoryService.getAllProductCategories());
    }

    @PostMapping("/product")
    public ResponseEntity<Object> addNewProductCategory(@Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity.ok(storeCategoryService.addNewProductCategory(productCategoryDTO));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProductCategory(@PathVariable Long id,
                                                        @RequestBody @Valid ProductCategoryDTO productCategoryDTO) {
        return ResponseEntity.ok(storeCategoryService.updateProductCategory(id, productCategoryDTO));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductCategory(@PathVariable Long id) {
        storeCategoryService.deleteProductCategory(id);
        return ResponseEntity.ok().build();
    }

}
