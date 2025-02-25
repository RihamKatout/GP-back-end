package gp.riham_aisha.back_end.model.product_and_configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gp.riham_aisha.back_end.enums.AttributeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configuration_attributes")
public class ConfigurationAttributes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Attribute name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private AttributeType type;

    @ElementCollection
    @CollectionTable(
            name = "attribute_choices",
            joinColumns = @JoinColumn(name = "attribute_id")
    )
    @Column(name = "choice")
    private List<Choice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "configuration_id")
    private ProductConfiguration configuration;
}
