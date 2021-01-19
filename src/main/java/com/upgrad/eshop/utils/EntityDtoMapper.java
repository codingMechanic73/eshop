package com.upgrad.eshop.utils;

import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    @Autowired
    private UserService userService;

    public ShippingAddressDto convertToShippingAddressResponse(com.upgrad.eshop.entities.ShippingAddress shippingAddress) {
        ShippingAddressDto addressResponse = new ShippingAddressDto();
        addressResponse.setId(shippingAddress.getId());
        addressResponse.setName(shippingAddress.getName());
        addressResponse.setPhone(shippingAddress.getPhone());
        addressResponse.setStreet(shippingAddress.getStreet());
        addressResponse.setLandmark(shippingAddress.getLandmark());
        addressResponse.setCity(shippingAddress.getCity());
        addressResponse.setState(shippingAddress.getState());
        addressResponse.setZipcode(shippingAddress.getZipcode());
        User user = userService.getLoggedInUser();
        addressResponse.setUser(user);
        return addressResponse;
    }

}
