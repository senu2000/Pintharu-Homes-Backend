package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.OrderDetailsDto;
import com.pintharuHomes.Backend.dto.OrderInputDto;
import com.pintharuHomes.Backend.entity.OrderDetail;
import com.pintharuHomes.Backend.service.OrderDetailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class OrderDetailsController {

    @Autowired
    private OrderDetailService orderDetailService;

    //        Build place order REST API
    @PostMapping("/placeOrder/{isCartCheckout}")
    public void placeOrder(@PathVariable("isCartCheckout") boolean isCartCheckout,
                           @RequestBody OrderInputDto orderInputDto){
        orderDetailService.placeOrder(orderInputDto, isCartCheckout);
    }

    //        Build get orders by Id REST API
    @GetMapping("/byId")
    public ResponseEntity<List<OrderDetailsDto>> getOrderDetailsById(){
        List<OrderDetailsDto> orderDetailsById = orderDetailService.getOrderDetailsById();
        return ResponseEntity.ok(orderDetailsById);
    };

    //        Build get all orders REST API
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDetailsDto>> getAllOrders(){
        List<OrderDetailsDto> allOrders = orderDetailService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    };

    //        Build mark order as dispatched REST API
    @GetMapping("/markOrderAsDispatched/{orderId}")
    public void markOrderAsDispatched(@PathVariable("orderId") Integer orderId){
        orderDetailService.markOrderAsDispatched(orderId);
    }
}
