package gp.riham_aisha.back_end.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "cartItems")
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private String details;
    private String message;
    private Long storeId;
    private String storeName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ToString.Exclude
    @OneToMany(mappedBy = "cartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConfigurationInstance> configurationInstances = new ArrayList<>();
}
