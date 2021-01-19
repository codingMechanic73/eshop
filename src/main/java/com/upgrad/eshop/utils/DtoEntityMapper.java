package com.upgrad.eshop.utils;


import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DtoEntityMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ShippingAddress convertToShippingAddressEntity(ShippingAddressDto addressRequest) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setName(addressRequest.getName());
        shippingAddress.setPhone(addressRequest.getPhone());
        shippingAddress.setStreet(addressRequest.getStreet());
        shippingAddress.setLandmark(addressRequest.getLandmark());
        shippingAddress.setCity(addressRequest.getCity());
        shippingAddress.setState(addressRequest.getState());
        shippingAddress.setZipcode(addressRequest.getZipcode());
        return shippingAddress;
    }

    public User convertToUserEntity(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRole(Constants.Roles.USER);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        return user;
    }

}