package com.kartoxa.dreamshops.service.cart;

import com.kartoxa.dreamshops.dto.CartDto;
import com.kartoxa.dreamshops.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    CartDto convertToDto(Cart cart);
    Cart getCartByUserId(Long userId);

}
