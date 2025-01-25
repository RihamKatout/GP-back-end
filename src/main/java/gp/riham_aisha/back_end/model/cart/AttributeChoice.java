package gp.riham_aisha.back_end.model.cart;

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
public class AttributeChoice implements Serializable {
    private Long attributeId;
    private String choiceName;
}
