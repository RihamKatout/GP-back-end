package gp.riham_aisha.back_end.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "invalid store category name")
    @NotEmpty(message = "empty store category")
    private String categoryName;

    public StoreCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}

