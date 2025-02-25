package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.enums.StoreStatus;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "stores")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String logoURL;
    private String coverURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    private StoreStatus status = StoreStatus.UNDER_REVIEW;
    private int numberOfReviews = 0;
    private double rating = 0;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

    @JsonIgnore
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Product> products = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private transient List<Review> reviews = new ArrayList<>();

    @JsonProperty("productCategories")
    public Set<ProductCategory> getProductCategories() {
        return storeCategory.getProductCategories();
    }

    @JsonProperty("storeCategoryId")
    public Long getStoreCategoryId() {
        return storeCategory.getId();
    }

    public Store(AddStoreDto addStoreDto, StoreCategory storeCategory, User manager) {
        name = addStoreDto.name();
        description = addStoreDto.description();
        logoURL = addStoreDto.logoURL();
        coverURL = addStoreDto.coverURL();
        this.storeCategory = storeCategory;
        this.manager = manager;
    }
    public void addReview(Review review) {
        this.numberOfReviews++;
        rating = (rating * (numberOfReviews - 1) + review.getRating()) / numberOfReviews;
    }
}
