package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.ProductRepository;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProduct(String category,
                                       String direction,
                                       String name,
                                       Integer pageNumber,
                                       Integer pageSize,
                                       String sortBy) {
        Pageable paginateAndSortBy = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        if (Sort.Direction.ASC.name().equals(direction)) {
            paginateAndSortBy = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        }

        return productRepository
                .findByNameContainingIgnoreCaseAndCategory(name, category, paginateAndSortBy);

    }

    @Override
    public Product saveProduct(Product product) {
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("No Product found for ID " + id + "!"));
    }

    @Override
    public Product updateProductById(Long id, Product upDatedProduct) throws ProductNotFoundException {
        return productRepository.findById(id).map(
                product -> {
                    if (upDatedProduct.getName() != null && !upDatedProduct.getName().isEmpty()) {
                        product.setName(upDatedProduct.getName());
                    }
                    if (upDatedProduct.getCategory() != null && !upDatedProduct.getCategory().isEmpty()) {
                        product.setCategory(upDatedProduct.getCategory());
                    }
                    if (upDatedProduct.getPrice() != null) {
                        product.setPrice(upDatedProduct.getPrice());
                    }
                    if (upDatedProduct.getDescription() != null && !upDatedProduct.getDescription().isEmpty()) {
                        product.setDescription(upDatedProduct.getDescription());
                    }
                    if (upDatedProduct.getManufacturer() != null && !upDatedProduct.getManufacturer().isEmpty()) {
                        product.setManufacturer(upDatedProduct.getManufacturer());
                    }
                    if (upDatedProduct.getAvailableItems() != null) {
                        product.setAvailableItems(upDatedProduct.getAvailableItems());
                    }
                    if (upDatedProduct.getImageUrl() != null && !upDatedProduct.getImageUrl().isEmpty()) {
                        product.setImageUrl(upDatedProduct.getImageUrl());
                    }
                    product.setProductId(id);
                    product.setUpdated(LocalDateTime.now());
                    return productRepository.save(product);
                }
        ).orElseThrow(() -> new ProductNotFoundException("No Product found for ID - " + id + "!"));
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("No Product found for ID - " + id + "!"));
        productRepository.delete(product);
    }

    @Override
    public Set<String> getProductCategories() {
        Set<String> categories = new HashSet<>();
        productRepository.findAll().forEach(product ->
                categories.add(product.getCategory()));
        return categories;
    }
}
