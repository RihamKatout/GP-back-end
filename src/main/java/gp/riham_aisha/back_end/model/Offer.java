package gp.riham_aisha.back_end.model;

import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean publicOffer = true;
    private String title;
    private String description;
    private String imageurl;
    private Double discount;
    private Timestamp endDate;
//    @ToString.Exclude
//    @OneToMany(mappedBy = "offer", orphanRemoval = true)
//    private List<Product> products = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
