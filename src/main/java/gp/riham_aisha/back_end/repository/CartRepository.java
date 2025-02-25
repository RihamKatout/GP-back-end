package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
}
