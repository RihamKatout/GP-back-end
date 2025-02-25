package gp.riham_aisha.back_end.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.product.ProductBasicInfo;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "product_order_configurations")
public class ProductOrderConfigurations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer quantity;
    private String message;
    private String note;
    private String description;
    private Long productIdTmp;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_order_id", nullable = false)
    private SubOrder subOrder;

    @JsonProperty("productInfo")
    public ProductBasicInfo getProductInfo() {
        return ProductBasicInfo.fromProduct(product);
    }

}