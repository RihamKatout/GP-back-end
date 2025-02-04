package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.order.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getUserOrders();
}
