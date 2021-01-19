package com.upgrad.eshop.services;

import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class OrderServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private ShippingAddressService shippingAddressService;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order orderSavedInDB;

    @BeforeEach
    public void setupMockito() throws ProductNotFoundException, ShippingAddressNotFoundException, UserNotFoundException, OrderNotFoundException {

        User userSavedInDB = new User(1L,
                "admin", "$2a$10$Z1cLZMH1ILAZPhg12wQCxu1HBn1rFqmRmt.L635yzX2j8rAjvwAx.",
                "ishwar",
                "soni",
                "admin.soni@upgrad.com",
                "1111111111",
                "USER",
                LocalDateTime.now(),
                LocalDateTime.now());

        Product productSavedInDB = new Product(1L,
                "automotive product",
                "Automotive",
                1000.00,
                "This is a cool automotive product",
                "Automotive manufacturer",
                20,
                "image url",
                LocalDateTime.now(),
                LocalDateTime.now());

        ShippingAddress shippingAddressSavedInDB = new ShippingAddress(1L,
                "John Snow",
                "9091929394",
                "The Castle Street",
                "Castle",
                "WinterFell",
                "The North",
                "123456",
                userService.findById(1L));

        orderSavedInDB = new Order(1L,
                userService.findById(1L),
                productService.getProductById(1L),
                shippingAddressService.getShippingAddressById(1L),
                15.56,
                LocalDateTime.now());

        Mockito.when(userService.findById(1L))
                .thenReturn(userSavedInDB);

        Mockito.when(productService.getProductById(1L))
                .thenReturn(productSavedInDB);

        Mockito.when(shippingAddressService.getShippingAddressById(1L))
                .thenReturn(shippingAddressSavedInDB);

        Mockito.when(orderService.getOrderById(1L))
                .thenReturn(orderSavedInDB);

        Mockito.when(orderService.saveOrder(new Order(1L,
                userService.findById(1L),
                productService.getProductById(1L),
                shippingAddressService.getShippingAddressById(1L),
                15.56,
                LocalDateTime.now())))
                .thenReturn(new Order(1L,
                        userService.findById(1L),
                        productService.getProductById(1L),
                        shippingAddressService.getShippingAddressById(1L),
                        15.56,
                        LocalDateTime.now()));
    }

    @Test
    public void testSaveOrder() throws ProductNotFoundException, ShippingAddressNotFoundException, UserNotFoundException {
        Order newOrder = new Order(1L,
                userService.findById(1L),
                productService.getProductById(1L),
                shippingAddressService.getShippingAddressById(1L),
                15.56,
                LocalDateTime.now());

        Order savedOrder = orderService.saveOrder(newOrder);
        Assertions.assertEquals(savedOrder, newOrder);
    }

    @Test
    public void testGetOrderById() throws OrderNotFoundException {
        Order order = orderService.getOrderById(1L);
        Assertions.assertEquals(orderSavedInDB, order);
    }

}