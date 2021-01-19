package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
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
class ShippingAddressServiceImplTest {

    @InjectMocks
    private ShippingAddressService shippingAddressService;

    @Mock
    private UserService userService;

    private ShippingAddressDto shippingAddressDto;
    private ShippingAddress shippingAddressSavedInDB;

    @BeforeEach
    public void setUpMockito() throws UserNotFoundException, ProductNotFoundException, ShippingAddressNotFoundException {

        shippingAddressSavedInDB = new ShippingAddress(1L,
                "John Snow",
                "9091929394",
                "The Castle Street",
                "Castle",
                "WinterFell",
                "The North",
                "123456",
                userService.findById(1L));

        shippingAddressDto = new ShippingAddressDto(1L,
                "John Snow",
                "9091929394",
                "The Castle Street",
                "Castle",
                "WinterFell",
                "The North",
                "123456",
                userService.findById(1L));

        User userSavedInDB = new User(1L,
                "admin", "$2a$10$Z1cLZMH1ILAZPhg12wQCxu1HBn1rFqmRmt.L635yzX2j8rAjvwAx.",
                "ishwar",
                "soni",
                "admin.soni@upgrad.com",
                "1111111111",
                "USER",
                LocalDateTime.now(),
                LocalDateTime.now());

        Mockito.when(userService.findById(1L))
                .thenReturn(userSavedInDB);

        Mockito.when(shippingAddressService.getShippingAddressById(1L))
                .thenReturn(shippingAddressSavedInDB);

        Mockito.when(shippingAddressService.addAddress(shippingAddressDto))
                .thenReturn(new ShippingAddress(1L,
                        "John Snow",
                        "9091929394",
                        "The Castle Street",
                        "Castle",
                        "WinterFell",
                        "The North",
                        "123456",
                        userService.findById(1L)));
    }

    @Test
    public void testSaveAddress() throws ProductNotFoundException, ShippingAddressNotFoundException {
        ShippingAddress shippingAddress = shippingAddressService.addAddress(shippingAddressDto);
        Assertions.assertEquals(shippingAddress.getName(), shippingAddressDto.getName());
    }

    @Test
    public void testGetAddressById() throws ShippingAddressNotFoundException {
        ShippingAddress shippingAddress = shippingAddressService.getShippingAddressById(1L);
        Assertions.assertEquals(shippingAddress, shippingAddressSavedInDB);
    }

}