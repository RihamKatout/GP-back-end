package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.OfferDto;
import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.dto.store.StoreBasicInfoDto;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@Table(name = "offers")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean publicOffer = true;
    private String title;
    private String description;
    private String imageurl;
    private Double discount;
    private LocalDateTime endDate;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "offer", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = true)
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonProperty("store_info")
    public StoreBasicInfoDto getStoreBasicInfoDto() {
        return store == null ? null
                : StoreBasicInfoDto.fromStore(store);
    }

    public Offer(OfferDto offerDto) {
        this.publicOffer = offerDto.publicOffer();
        this.title = offerDto.title();
        this.description = offerDto.description();
        this.imageurl = offerDto.imageurl();
        this.discount = offerDto.discount();
        this.endDate = offerDto.endDate();
    }

    public void update(OfferDto offerDto) {
        this.publicOffer = offerDto.publicOffer();
        this.title = offerDto.title();
        this.description = offerDto.description();
        this.imageurl = offerDto.imageurl();
        this.discount = offerDto.discount();
        this.endDate = offerDto.endDate();
    }
}
