package gp.riham_aisha.back_end.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import gp.riham_aisha.back_end.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    private double rating = 0;

    private Boolean isAvailable = true;
    private Boolean isCustomizable = false;
    private String model3dURL;

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
    private String storeName;
    private Long storeIdTmp;

    public Product(ProductDto productDto, Store store, ProductCategory productCategory) {
        update(productDto, store, productCategory);
        this.storeName = store.getName();
        this.storeIdTmp = store.getId();
    }

    public void update(ProductDto productDto, Store store, ProductCategory productCategory){
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
    }
}
