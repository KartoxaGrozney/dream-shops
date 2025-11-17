package com.kartoxa.dreamshops.repository;

import com.kartoxa.dreamshops.model.Cart;
import com.kartoxa.dreamshops.model.CartItem;
import com.kartoxa.dreamshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long id);
}
