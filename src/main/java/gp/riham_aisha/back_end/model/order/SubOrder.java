package gp.riham_aisha.back_end.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.store.StoreBasicInfoDto;
import gp.riham_aisha.back_end.enums.OrderStatusEnum;
import gp.riham_aisha.back_end.model.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "sub_order")
public class SubOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private Date orderDate;
    private Date deliveryDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "subOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrderConfigurations> products = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Long storeIdTmp;

    @JsonProperty("storeInfo")
    public StoreBasicInfoDto getStoreInfo() {
        return StoreBasicInfoDto.fromStore(store);
    }
}
