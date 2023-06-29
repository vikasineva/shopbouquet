package com.ua.rosella.controller;

import com.ua.rosella.model.Order;
import com.ua.rosella.request.OrderRequest;
import com.ua.rosella.service.AuthenticationService;
import com.ua.rosella.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "/order/checkout", produces = "application/json")
    public ResponseEntity<?> checkoutOrder(@RequestBody OrderRequest order, HttpServletRequest request){
        var customer = authenticationService.getAuthUser(request);
        if(customer==null){
            return new ResponseEntity<>("Доступ заборонено. Зареєструйтеся або увійдіть в аккаунт",HttpStatus.UNAUTHORIZED);
        }
        orderService.save(order, customer);
        return ResponseEntity.ok(order);
    }
}
