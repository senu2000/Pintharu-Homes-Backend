package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.OrderDetailsDto;
import com.pintharuHomes.Backend.dto.OrderInputDto;
import com.pintharuHomes.Backend.entity.TransactionDetails;

import java.util.List;

public interface OrderDetailService {

    void placeOrder(OrderInputDto orderInputDto, boolean isCartCheckout);

    List<OrderDetailsDto> getOrderDetailsById();

    List<OrderDetailsDto> getAllOrders();

    void markOrderAsDispatched(Integer orderId);

    TransactionDetails createTransaction(Integer amount);

}
