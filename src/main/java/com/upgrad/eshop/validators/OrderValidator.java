package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

@Component
public interface OrderValidator {
    public void validateOrderRequest(OrderRequest orderRequest) throws APIException;

//  TODO: Create the implementing class 'OrderValidatorImpl' in the validators package to define the required methods
}
