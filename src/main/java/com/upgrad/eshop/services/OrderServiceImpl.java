package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    @Override
    public Order addOrder(OrderRequest orderRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        return null;
    }

    @Override
    public Order getOrderById(Long id) throws OrderNotFoundException {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
