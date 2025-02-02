package gp.riham_aisha.back_end.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gp.riham_aisha.back_end.dto.product.ProductDetailsDto;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.dto.product.SearchProductParameters;
import gp.riham_aisha.back_end.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) Boolean is3dCustomizable

    ) {
        Pageable pageable = PageRequest.of(page, size);
        SearchProductParameters searchProductParameters = new SearchProductParameters(keyWord, categoryId, storeId,
                storeCategoryId, available, threeDModel, is3dCustomizable, minPrice, maxPrice, minRating, id, customizable);
        return ResponseEntity.ok(productService.searchProducts(searchProductParameters, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetailsById(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> addProduct(
            @RequestPart("image") MultipartFile image,
            @RequestPart("request") String requestJson) throws IOException {
        ProductManagementDto productDto = new ObjectMapper().readValue(requestJson, ProductManagementDto.class);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addProduct(productDto, image));
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
