package gp.riham_aisha.back_end.model.product_and_configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.model.ProductCategory;
import gp.riham_aisha.back_end.model.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {

    // ------------------ Product basic info ------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Main image URL is required")
    private String mainImageURL;

    @NotNull
    private double basePrice = 0;

    // ------------------ Product availability ------------------
    private Integer stock = 0;

    private Integer stockEdge = 0;

    @NotNull
    private Boolean needStock = false;

    private Boolean isAvailable = true;

    // ------------------ Product customization ------------------
    private String model3dURL;

    private Boolean is3dCustomizable = false;

    private Boolean defaultFeatures = true;

    // ------------------ Product rating ------------------
    private Double rating = 0.0;

    private Integer numberOfReviews = 0;

    // ------------------ store & product category ------------------
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @JsonProperty("categoryId")
    public Long getCategoryId() {
        return productCategory != null ? productCategory.getId() : null;
    }

    // ------------------ Product configurations ------------------
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductConfiguration> configurations = new ArrayList<>();

    // Constructor to create a product with necessary details and configurations
    public Product(ProductManagementDto productDto, Store store, ProductCategory productCategory) {
        if (productDto == null || store == null || productCategory == null) {
            throw new IllegalArgumentException("Product data, store, and category cannot be null");
        }
        this.store = store;
        update(productDto, productCategory);
    }

    // Method to update the product with new data
    public void update(ProductManagementDto productDto, ProductCategory productCategory) {
        if (productDto == null || store == null || productCategory == null) {
            throw new IllegalArgumentException("Product data, store, and category cannot be null");
        }

        this.productCategory = productCategory;

        // Updating product basic information
        this.name = productDto.product().getName();
        this.description = productDto.product().getDescription();
        this.mainImageURL = productDto.product().getMainImageURL();
        this.basePrice = productDto.product().getBasePrice();

        // Updating product availability
        this.stock = productDto.product().getStock() != null ? productDto.product().getStock() : this.stock;
        this.stockEdge = productDto.product().getStockEdge() != null ? productDto.product().getStockEdge() : this.stockEdge;
        this.needStock = productDto.product().getNeedStock() != null ? productDto.product().getNeedStock() : this.needStock;
        this.isAvailable = productDto.product().getIsAvailable() != null ? productDto.product().getIsAvailable() : this.isAvailable;

        // Updating customization details
        this.model3dURL = productDto.product().getModel3dURL();
        this.is3dCustomizable = productDto.product().getIs3dCustomizable();
        this.defaultFeatures = productDto.product().getDefaultFeatures();

        // Rating and reviews information
        this.rating = (productDto.product().getRating() != null) ? productDto.product().getRating() : this.rating;
        this.numberOfReviews = productDto.product().getNumberOfReviews() != null ? productDto.product().getNumberOfReviews() : this.numberOfReviews;
    }
}
