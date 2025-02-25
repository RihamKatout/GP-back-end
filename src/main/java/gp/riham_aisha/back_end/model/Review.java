package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.UserBasicInfoDto;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
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
@Table(name = "reviews")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedback;
    private int rating;
    private Date date;
    private Boolean isProductReview; // if no, then it's a store review
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonProperty("userInfo")
    public UserBasicInfoDto userInfo() {
        return UserBasicInfoDto.fromUser(user);
    }

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(String feedback, int rating, Boolean isProductReview, User user) {
        this.feedback = feedback;
        this.rating = rating;
        this.date  = new Date();
        this.isProductReview = isProductReview;
        this.user = user;
    }
}
