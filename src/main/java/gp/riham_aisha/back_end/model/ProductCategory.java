package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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

    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid store category name")
    @NotEmpty(message = "empty store category")
    private String name;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    public ProductCategory(String name, StoreCategory storeCategory) {
        this.name = name;
        this.storeCategory = storeCategory;
    }
}
