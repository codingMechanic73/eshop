package com.upgrad.eshop.services;


import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;

public interface ShippingAddressService {

    ShippingAddress addAddress(ShippingAddressDto addressRequest) throws ProductNotFoundException, ShippingAddressNotFoundException;

    ShippingAddress getShippingAddressById(Long id) throws ShippingAddressNotFoundException;
}
