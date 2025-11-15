package com.kartoxa.dreamshops.service.cart;

import com.kartoxa.dreamshops.exceptions.ResourceNotFoundException;
import com.kartoxa.dreamshops.model.Cart;
import com.kartoxa.dreamshops.repository.CartItemRepository;
import com.kartoxa.dreamshops.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
//
//    @Override
//    public Cart getCart(Long id) {
//        Cart cart = cartRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
//        BigDecimal totalAmount = cart.getTotalAmount();
//        cart.setTotalAmount(totalAmount);
//        return cartRepository.save(cart);
//    }
//
//    @Override
//    @Transactional
//    public void clearCart(Long id) {
//        Cart cart = getCart(id);
//        cartItemRepository.deleteAllByCartId(id);
//        cart.getItems().clear();
//        cartRepository.deleteById(id);
//    }
//
//    @Override
//    public BigDecimal getTotalPrice(Long id) {
//        Cart cart = getCart(id);
//        return cart.getTotalAmount();
//    }
}
