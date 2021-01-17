package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorImpl implements OrderValidator {
    @Override
    public void validateOrderRequest(OrderRequest orderRequest) throws APIException {

    }
}
