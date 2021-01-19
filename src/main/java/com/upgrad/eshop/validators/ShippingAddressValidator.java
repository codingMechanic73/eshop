package com.upgrad.eshop.validators;


import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.exceptions.APIException;

public interface ShippingAddressValidator {

    void validateShippingAddressRequest(ShippingAddressDto addressRequest) throws APIException;

}
