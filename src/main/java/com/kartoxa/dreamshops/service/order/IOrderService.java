package com.kartoxa.dreamshops.service.order;

import com.kartoxa.dreamshops.model.Order;
import com.kartoxa.dreamshops.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
