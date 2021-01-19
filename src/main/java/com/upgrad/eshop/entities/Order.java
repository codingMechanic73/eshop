package com.upgrad.eshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESHOP_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Product product;

    @ManyToOne
    private ShippingAddress shippingAddress;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

}
