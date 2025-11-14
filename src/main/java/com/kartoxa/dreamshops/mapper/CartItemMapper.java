package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.CartItemDto;
import com.kartoxa.dreamshops.model.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {
    public CartItemDto toDto(CartItem cartItem){
        if(cartItem == null){
            return null;
        }

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());

        if(cartItem != null){
            cartItemDto.setProductId(cartItem.getProduct().getId());
            cartItemDto.setProductName(cartItem.getProduct().getName());
            cartItemDto.setPrice(cartItem.getProduct().getPrice());

            // Рассчитываем subtotal (цена * количество)
            BigDecimal subtotal = cartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            cartItemDto.setSubtotal(subtotal);
        }
        return cartItemDto;
    }

    public List<CartItemDto> toDtoList(List<CartItem> cartItems) {
        if (cartItems == null) {
            return null;
        }

        return cartItems.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
