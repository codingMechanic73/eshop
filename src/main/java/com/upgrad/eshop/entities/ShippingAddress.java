package com.upgrad.eshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ESHOP_SHIPPING_ADDRESS")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String landmark;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipcode;

    @OneToOne
    private User user;

}
