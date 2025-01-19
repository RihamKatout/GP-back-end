package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.enums.Size;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double price = 0;

    @NotNull
    private int stock = 0;
    private String imageurl;

    private double rating = 0;
    private int numberOfReviews = 0;

    private Boolean isAvailable = true;
    private Boolean isCustomizable = false;
    private String model3dURL;
    private Integer stockEdge;
    private Boolean inWishlist = false;
    private List<String> colors;
    @ElementCollection
    private Map<Size, Double> sizePrices;

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

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @JsonProperty("storeName")
    public String getStoreName() {
        return store.getName();
    }

    @JsonProperty("storeIdTmp")
    public Long getStoreIdTmp() {
        return store.getId();
    }

    @JsonProperty("storeLogoUrl")
    public String getStoreLogoUrl() {
        return store.getLogoURL();
    }

    @JsonProperty("categoryId")
    public Long getCategoryId() {
        return productCategory.getId();
    }

    public Product(ProductDto productDto, Store store, ProductCategory productCategory) {
        update(productDto, store, productCategory);
    }

    public void update(ProductDto productDto, Store store, ProductCategory productCategory) {
        this.name = productDto.name();
        this.description = productDto.description();
        this.price = productDto.price();
        this.stock = productDto.stock();
        this.imageurl = productDto.imageurl();
        this.isAvailable = productDto.isAvailable();
        this.isCustomizable = productDto.isCustomizable();
        this.model3dURL = productDto.model3dURL();
        this.store = store;
        this.productCategory = productCategory;
        this.colors = productDto.colors();
        this.sizePrices = productDto.sizePrices();
        this.stockEdge = productDto.stockEdge();
    }
}
