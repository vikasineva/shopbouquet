package com.ua.rosella.service;

import com.ua.rosella.model.Order;
import com.ua.rosella.model.OrderStatus;
import com.ua.rosella.model.User;
import com.ua.rosella.repository.OrderRepository;
import com.ua.rosella.request.OrderRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository repository;

    @Autowired
    private final BouquetService bouquetService;

    public OrderService(OrderRepository repository, BouquetService bouquetService) {
        this.repository = repository;
        this.bouquetService = bouquetService;
    }

    public void save(OrderRequest request, User customer){
        var order = new Order(request);
        order.getHistory().add(
                new Order.StatusStamp(
                        OrderStatus.NEW,
                        new Date()
                )
        );
        order.setCustomer(customer);
        order.setPaid(true); // will be changed when the payment system is added
        order.setId(new ObjectId());
        order.setOrderNumber(order.getId().getTimestamp());
        repository.save(order);
    }
}
