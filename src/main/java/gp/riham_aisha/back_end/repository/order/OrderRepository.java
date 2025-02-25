package gp.riham_aisha.back_end.repository.order;

import gp.riham_aisha.back_end.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySubOrders_Store_Id(Long id);
    List<Order> findByUser_Username(String username);
}
