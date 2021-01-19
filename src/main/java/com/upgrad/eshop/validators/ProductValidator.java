package com.upgrad.eshop.validators;

import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

public interface ProductValidator {

    void validateProductRequest(Product productRequest) throws APIException;

}
