package gp.riham_aisha.back_end.model.product_and_configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "configurations")
@NoArgsConstructor
@AllArgsConstructor
public class ProductConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Configuration name is required")
    private String name;

    @NotNull
    private Boolean allowsMultipleUnits = false;

    private Double unitPriceImpact = 0.0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ConfigurationAttributes> configurationAttributes = new ArrayList<>();
}
