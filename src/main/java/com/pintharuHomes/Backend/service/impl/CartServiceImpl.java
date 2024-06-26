package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.CartDto;
import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.entity.Cart;
import com.pintharuHomes.Backend.entity.Paint;
import com.pintharuHomes.Backend.entity.User;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.filter.JwtAuthenticationFilter;
import com.pintharuHomes.Backend.mapper.PaintMapper;
import com.pintharuHomes.Backend.mapper.UserMapper;
import com.pintharuHomes.Backend.repository.CartRepository;
import com.pintharuHomes.Backend.repository.PaintRepository;
import com.pintharuHomes.Backend.repository.UserRepository;
import com.pintharuHomes.Backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaintRepository paintRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart addToCart(Integer paintID) {
        Paint paint = paintRepository.findById(paintID)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for paint id:" + paintID));

        String username = JwtAuthenticationFilter.CURRENT_USER;
//        System.out.println(username);
        User user = null;
        if (username != null) {
            user = userRepository.findByUsername(username).orElseThrow();
        }

        List<Cart> cartList = cartRepository.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(item -> item.getPaint().getId() == paintID)
                .collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            return null;
        }

        if (paint != null && user != null) {
            Cart cart = new Cart(paint, user);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public List<CartDto> getAllCartDetails() {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user exist for username:" + username));

        List<Cart> cartItems = cartRepository.findByUser(user);
//        System.out.println("Fetched cart items: " + cartItems);

        return cartItems
                .stream()
                .map(cart -> new CartDto(cart.getId(), PaintMapper.mapToPaintDto(cart.getPaint()), UserMapper.mapToUserDto(cart.getUser())))
                .collect(Collectors.toList());
    }

    @Override
    public Stream<PaintDto> getCheckoutDetails(boolean isSinglePaint, Integer paintID) {
        if (isSinglePaint && paintID != 0) {
//            for single paint checkout
            return null;
        }else {
            String username = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("No user exist for username:" + username));

            List<Cart> cartItems = cartRepository.findByUser(user);
            return cartItems.stream().map(cart -> PaintMapper.mapToPaintDto(cart.getPaint()));
        }
    }

    @Override
    public void deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()-> new ResourceNotFoundException("No cart exists for id:" + cartId));
        cartRepository.deleteById(cartId);
    }
}
