package gp.riham_aisha.back_end.controller;

import gp.riham_aisha.back_end.dto.SearchProductParameters;
import gp.riham_aisha.back_end.dto.product.ProductDetailsDto;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<ProductWithStoreDto>> getProducts(
            @RequestParam(required = false) String keyWord, @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long storeId, @RequestParam(required = false) Long storeCategoryId,
            @RequestParam(required = false) Boolean available, @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Boolean customizable, @RequestParam(required = false) Boolean threeDModel,
            @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Double minRating, Long id,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size

    ) {
        Pageable pageable = PageRequest.of(page, size);
        SearchProductParameters searchProductParameters = new SearchProductParameters(keyWord, categoryId, storeId,
                storeCategoryId, available, threeDModel, customizable, minPrice, maxPrice, minRating, id);
        return ResponseEntity.ok(productService.searchProducts(searchProductParameters, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetailsById(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> addProduct(@RequestBody @Valid ProductManagementDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addProduct(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductManagementDto> updateProduct(@PathVariable Long id, @RequestBody ProductManagementDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
