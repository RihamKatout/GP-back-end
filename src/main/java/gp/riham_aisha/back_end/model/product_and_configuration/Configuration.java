package gp.riham_aisha.back_end.model.product_and_configuration;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "configurations")
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean allowsMultipleUnits;

    private Double unitPriceImpact = 0.0;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ConfigurationAttributes> configurationAttributes = new ArrayList<>();
}
