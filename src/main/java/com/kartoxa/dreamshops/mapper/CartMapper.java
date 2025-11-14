package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.CartDto;
import com.kartoxa.dreamshops.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();

        dto.setId(cart.getId());
        if(cart.getUser() != null){
            dto.setUserId(cart.getUser().getId());
        }

        if (cart.getItems() != null) {
            dto.setItems(cartItemMapper.toDtoList(cart.getItems()));
        } else {
            dto.setItems(new ArrayList<>());
        }

        BigDecimal totalPrice = calculateTotalPrice(cart);
        dto.setTotalPrice(totalPrice);

        return dto;
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return cart.getItems().stream()
                .filter(item -> item.getProduct() != null)  // Безопасность
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
