package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.CartDto;
import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.entity.Cart;

import java.util.List;
import java.util.stream.Stream;

public interface CartService {

    Cart addToCart(Integer paintID);

    List<CartDto> getAllCartDetails();

    Stream<PaintDto> getCheckoutDetails(boolean isSinglePaint, Integer paintID);

    void deleteCart(Integer cartId);
}
