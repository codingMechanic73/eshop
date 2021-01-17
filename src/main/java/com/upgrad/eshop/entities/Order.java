package com.upgrad.eshop.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESHOP_ORDER")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;

//  TODO: Define the 'Product' field with reference to the Product entity and use the necessary relationship annotation

//  TODO: Define the 'shippingAddress' field with reference to the ShippingAddress entity and use the necessary relationship annotation

    private Double amount;
    private LocalDateTime orderDate = LocalDateTime.now();

//  TODO: Generate getters & setters and constructors for the Entity class

}
