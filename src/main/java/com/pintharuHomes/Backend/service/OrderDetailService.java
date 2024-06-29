package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.OrderDetailsDto;
import com.pintharuHomes.Backend.dto.OrderInputDto;

import java.util.List;
import java.util.stream.Stream;

public interface OrderDetailService {

    void placeOrder(OrderInputDto orderInputDto, boolean isCartCheckout);

    List<OrderDetailsDto> getOrderDetailsById();

    List<OrderDetailsDto> getAllOrders();

    void markOrderAsDispatched(Integer orderId);
}
