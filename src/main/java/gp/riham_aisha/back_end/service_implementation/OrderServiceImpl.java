package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.model.order.Order;
import gp.riham_aisha.back_end.repository.order.OrderRepository;
import gp.riham_aisha.back_end.repository.order.ProductOrderConfigurationsRepo;
import gp.riham_aisha.back_end.repository.order.SubOrderRepository;
import gp.riham_aisha.back_end.service.OrderService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final StoreService storeService;
    private final ProductService productService;
    private final ProductOrderConfigurationsRepo productOrderConfigurationsRepo;
    private final SubOrderRepository subOrderRepository;
    @Transactional
    @Override
    public void addOrder(Order order) {
        User user = userService.getUserByUsername(AuthUtil.getCurrentUser());
        order.setUser(user);
        order.getSubOrders().forEach(subOrder -> {
            subOrder.setStore(storeService.getStore(subOrder.getStoreIdTmp()));
            subOrder.setOrder(order);
            subOrder.getProducts().forEach(productOrderConfigurations -> {
                productOrderConfigurations.setSubOrder(subOrder);
                productOrderConfigurations.setProduct(productService.getProductById(productOrderConfigurations.getProductIdTmp()));
            });
        });
        orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders() {
        return orderRepository.findByUser_Username(AuthUtil.getCurrentUser());
    }


}
