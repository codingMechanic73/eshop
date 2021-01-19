package com.upgrad.eshop.services;

import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ProductService {

    Page<Product> searchProduct(String category,
                                String direction,
                                String name,
                                Integer pageNumber,
                                Integer pageSize,
                                String sortBy);

    Product saveProduct(Product product);

    Product getProductById(Long id) throws ProductNotFoundException;

    Product updateProductById(Long id, Product product) throws ProductNotFoundException;

    void deleteProduct(Long id) throws ProductNotFoundException;

    Set<String> getProductCategories();
}
