package gp.riham_aisha.back_end.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "configuration_instances")
public class ConfigurationInstance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long configurationId;

    @ElementCollection
    @CollectionTable(
            name = "cart_attribute_choices",
            joinColumns = @JoinColumn(name = "configuration_instance_id")
    )
    private List<AttributeChoice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "cart_item_id", nullable = false)
    private CartItem cartItem;

}
