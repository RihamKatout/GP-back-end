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
    private Date creationDate;
    private StoreStatus status;
    private int numberOfReviews;
    private double rating;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

    public Store(StoreDto storeDto, StoreCategory storeCategory, User manager) {
        name = storeDto.name();
        description = storeDto.description();
        logoURL = storeDto.logoURL();
        coverURL = storeDto.coverURL();
        creationDate = new Date();
        status = StoreStatus.UNDER_REVIEW;
        numberOfReviews = 0;
        rating = 0;
        this.storeCategory = storeCategory;
        this.manager = manager;
    }
}
