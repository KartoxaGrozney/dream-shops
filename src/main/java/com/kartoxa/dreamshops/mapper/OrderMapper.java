package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.OrderDto;
import com.kartoxa.dreamshops.dto.OrderItemDto;
import com.kartoxa.dreamshops.model.Order;
import com.kartoxa.dreamshops.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderStatus", target = "items")
    @Mapping(source = "orderItems", target = "items")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    List<OrderDto> ordersToOrderDtos(List<Order> orders);
}
