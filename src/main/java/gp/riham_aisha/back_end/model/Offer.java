package gp.riham_aisha.back_end.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

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

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
