package com.kartoxa.dreamshops.repository;

import com.kartoxa.dreamshops.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
