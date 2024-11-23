package gp.riham_aisha.back_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "store_categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_store_category_name", columnNames = "categoryName"),
        }
)
public class StoreCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid store category name")
    @NotEmpty(message = "empty store category")
    private String categoryName;

    @OneToMany(mappedBy = "storeCategory", orphanRemoval = true)
    @ToString.Exclude
    private Set<ProductCategory> productCategories = new LinkedHashSet<>();

    public StoreCategory(String categoryName) {
        this.categoryName = categoryName;
    }

}

