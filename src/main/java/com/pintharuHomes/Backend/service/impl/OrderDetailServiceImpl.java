package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.OrderDetailsDto;
import com.pintharuHomes.Backend.dto.OrderInputDto;
import com.pintharuHomes.Backend.dto.OrderPaintQuantity;
import com.pintharuHomes.Backend.entity.*;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.filter.JwtAuthenticationFilter;
import com.pintharuHomes.Backend.mapper.PaintMapper;
import com.pintharuHomes.Backend.mapper.UserMapper;
import com.pintharuHomes.Backend.repository.CartRepository;
import com.pintharuHomes.Backend.repository.OrderDetailRepository;
import com.pintharuHomes.Backend.repository.PaintRepository;
import com.pintharuHomes.Backend.repository.UserRepository;
import com.pintharuHomes.Backend.service.OrderDetailService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final String ORDER_PLACED = "Placed";

    private static final String KEY = "rzp_test_BxmitsofMpSHns";
    private static final String KEY_SECRET = "37oC8yh4pENSNPhmnnAJSyls";
    private static final String CURRENCY = "LKR";

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private PaintRepository paintRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void placeOrder(OrderInputDto orderInputDto, boolean isCartCheckout) {
        List<OrderPaintQuantity> paintQuantityList = orderInputDto.getOrderPaintQuantityList();

        for (OrderPaintQuantity o : paintQuantityList) {
            Paint paint = paintRepository.findById(o.getPaintId())
                    .orElseThrow(()-> new ResourceNotFoundException("No paint found"));

            String username = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("No user exist for username:" + username));

            OrderDetail orderDetail = new OrderDetail(
                    orderInputDto.getFullName(),
                    orderInputDto.getFullAddress(),
                    orderInputDto.getContactNumber(),
                    ORDER_PLACED,
                    paint.getPrice() * o.getQuantity(),
                    paint,
                    user,
                    orderInputDto.getRazorpayPaymentId()
            );

            if (isCartCheckout) {
                List<Cart> cartList = cartRepository.findByUser(user);
                cartList.stream().forEach(cart -> cartRepository.deleteById(cart.getId()));
            }

            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public List<OrderDetailsDto> getOrderDetailsById() {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No user exist for username:" + username));

        List<OrderDetail> orders = orderDetailRepository.findByUser(user);
        return orders.stream().map(
                order -> new OrderDetailsDto(
                        order.getId(),
                        order.getOrderFullName(),
                        order.getOrderFullAddress(),
                        order.getOrderContactNo(),
                        order.getOrderStatus(),
                        order.getOrderAmount(),
                        PaintMapper.mapToPaintDto(order.getPaint()),
                        UserMapper.mapToUserDto(order.getUser())
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsDto> getAllOrders() {
        List<OrderDetail> allOrders = orderDetailRepository.findAll();
        return allOrders.stream().map(
                order -> new OrderDetailsDto(
                        order.getId(),
                        order.getOrderFullName(),
                        order.getOrderFullAddress(),
                        order.getOrderContactNo(),
                        order.getOrderStatus(),
                        order.getOrderAmount(),
                        PaintMapper.mapToPaintDto(order.getPaint()),
                        UserMapper.mapToUserDto(order.getUser())
                )
        ).collect(Collectors.toList());
    }

    @Override
    public void markOrderAsDispatched(Integer orderId) {
        try {
            OrderDetail orderDetail = orderDetailRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("No order exist for Id:" + orderId));
            if (orderDetail != null) {
                orderDetail.setOrderStatus("Dispatched");
                orderDetailRepository.save(orderDetail);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public TransactionDetails createTransaction(Integer amount) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100));
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
            Order order = razorpayClient.orders.create(jsonObject);
//            System.out.println(order);
            return prepareTransactionDetails(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        return new TransactionDetails(orderId, currency, amount, KEY);
    }
}
