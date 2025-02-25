package gp.riham_aisha.back_end.model.product_and_configuration;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Choice  implements Serializable {
    private String name;
    private Double priceImpact;
}
