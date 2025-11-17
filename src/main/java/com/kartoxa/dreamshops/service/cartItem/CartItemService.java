package com.kartoxa.dreamshops.service.cartItem;

import com.kartoxa.dreamshops.exceptions.ResourceNotFoundException;
import com.kartoxa.dreamshops.model.Cart;
import com.kartoxa.dreamshops.model.CartItem;
import com.kartoxa.dreamshops.model.Product;
import com.kartoxa.dreamshops.repository.CartItemRepository;
import com.kartoxa.dreamshops.repository.CartRepository;
import com.kartoxa.dreamshops.repository.ProductRepository;
import com.kartoxa.dreamshops.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartRepository.addCartItemToCart(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        cart.removeItem(cartItem);
        cartRepository.save(cart);
    }

//    @Override
//    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
//
//    }
//
//    @Override
//    public CartItem getCartItem(Long cartId, Long productId) {
//        return null;
//    }
//
//    @Override
//    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
//        Cart cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
//
//        cart.getItems()
//                .stream()
//                .filter(item -> item.getProduct().getId().equals(productId))
//                .findFirst()
//                .ifPresent(item -> {
//                    item.setQuantity(quantity);
//                    item.setTotalPrice();
//                });
//
//        BigDecimal totalAmount = cart.getItems()
//                .stream()
//                .map(CartItem::getTotalPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        cart.setTotalAmount(totalAmount);
//        cartRepository.save(cart);
//    }
//
//    @Override
//    public CartItem getCartItem(Long cartId, Long productId) {
//        return null;
//    }
}
