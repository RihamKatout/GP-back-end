package gp.riham_aisha.back_end.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.UserBasicInfoDto;
import gp.riham_aisha.back_end.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Double totalPrice;
    private Double deliveryCost;
    private String deliveryAddress;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubOrder> subOrders = new ArrayList<>();

    @JsonProperty("userInfo")
    public UserBasicInfoDto getUserInfo() {
        return UserBasicInfoDto.fromUser(user);
    }
}
