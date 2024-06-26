package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.CartDto;
import com.pintharuHomes.Backend.dto.PaintDto;
import com.pintharuHomes.Backend.entity.Cart;
import com.pintharuHomes.Backend.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //        Build add to cart REST API
    @GetMapping("/addToCart/{paintId}")
    public ResponseEntity<Cart> addToCart(@PathVariable("paintId") Integer paintId){
        Cart cart = cartService.addToCart(paintId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    //        Build get all cart items REST API
    @GetMapping("/allCartDetails")
    public ResponseEntity<List<CartDto>> getAllCartDetails(){
        List<CartDto> allCartDetails = cartService.getAllCartDetails();
        return ResponseEntity.ok(allCartDetails);
    }

    //        Build checkout REST API
    @GetMapping("/checkoutDetails/{isSinglePaint}/{paintID}")
    public ResponseEntity<List<PaintDto>> getCheckoutDetails(@PathVariable("isSinglePaint") boolean isSinglePaint,
                                                             @PathVariable("paintID") Integer paintID){
        Stream<PaintDto> checkoutDetails = cartService.getCheckoutDetails(isSinglePaint, paintID);
        return ResponseEntity.ok(checkoutDetails.toList());
    }

    //        Build delete cart REST API
    @DeleteMapping("{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable("cartId") Integer cartId){
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}
