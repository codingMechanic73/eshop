package com.upgrad.eshop.dtos;

import com.upgrad.eshop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressDto {

    private Long id;
    private String name;
    private String phone;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String zipcode;
    private User user;

}
