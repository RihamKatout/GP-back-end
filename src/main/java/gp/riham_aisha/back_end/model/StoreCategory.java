package gp.riham_aisha.back_end.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "invalid store category name")
    @NotEmpty(message = "empty store category")
    private String name;

    @OneToMany(mappedBy = "storeCategory", orphanRemoval = false)
    @ToString.Exclude
    @JsonIgnore
    private Set<ProductCategory> productCategories = new LinkedHashSet<>();

    private String imageurl;

    @OneToMany(mappedBy = "storeCategory")
    @ToString.Exclude
    @JsonIgnore
    private Set<Store> stores = new LinkedHashSet<>();

    public StoreCategory(String name, String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

}

