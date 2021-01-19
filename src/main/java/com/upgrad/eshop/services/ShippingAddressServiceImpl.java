package com.upgrad.eshop.services;


import com.upgrad.eshop.daos.ShippingAddressRepository;
import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.utils.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private DtoEntityMapper dtoEntityMapper;

    @Override
    public ShippingAddress addAddress(ShippingAddressDto addressRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        ShippingAddress shippingAddress = dtoEntityMapper.convertToShippingAddressEntity(addressRequest);
        shippingAddressRepository.save(shippingAddress);
        return shippingAddress;
    }

    @Override
    public ShippingAddress getShippingAddressById(Long id) throws ShippingAddressNotFoundException {
        return shippingAddressRepository
                .findById(id)
                .orElseThrow(() -> new ShippingAddressNotFoundException("No Address found for ID - " + id + "!"));
    }

}
