package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gp.riham_aisha.back_end.dto.StoreDto;
import gp.riham_aisha.back_end.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public Store(StoreDto storeDto, StoreCategory storeCategory, User manager) {
        name = storeDto.name();
        description = storeDto.description();
        logoURL = storeDto.logoURL();
        coverURL = storeDto.coverURL();
        this.storeCategory = storeCategory;
        this.manager = manager;
    }
}
