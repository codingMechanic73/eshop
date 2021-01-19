package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.OrderRepository;
import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    UserService userService;

    @Override
    public Order addOrder(OrderRequest orderRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        Order order = new Order();
        Product product = productService.getProductById(orderRequest.getProductId());
        ShippingAddress shippingAddress = shippingAddressService.getShippingAddressById(orderRequest.getAddressId());
        order.setProduct(product);
        order.setShippingAddress(shippingAddress);

        if (product.getAvailableItems() == 0) {
            throw new ProductNotFoundException("Product with ID - " + orderRequest.getProductId() + "  is currently out of stock!");
        }
        order.setUser(userService.getLoggedInUser());
        order.setAmount(product.getPrice());
        order.setOrderDate(LocalDateTime.now());

        product.setAvailableItems(product.getAvailableItems() - 1);
        productService.saveProduct(product);
        return saveOrder(order);
    }

    @Override
    public Order getOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No Order found for ID - " + id + "!"));
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

}
