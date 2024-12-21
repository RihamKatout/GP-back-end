package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "product_categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_product_category_name", columnNames = "name"),
        }
)
public class ProductCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "invalid store category name")
    @NotEmpty(message = "empty store category")
    private String name;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;


    @JsonIgnore
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Product> products = new LinkedHashSet<>();

    public ProductCategory(String name, StoreCategory storeCategory) {
        this.name = name;
        this.storeCategory = storeCategory;
    }
}
