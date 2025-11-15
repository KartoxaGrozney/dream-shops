package com.kartoxa.dreamshops.repository;

import com.kartoxa.dreamshops.model.Cart;
import com.kartoxa.dreamshops.model.CartItem;
import com.kartoxa.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long id);
    Cart addCartItemToCart(CartItem cartItem);
}
