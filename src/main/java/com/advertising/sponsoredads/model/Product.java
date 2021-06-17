package com.advertising.sponsoredads.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class Product {

    @Column(name = "product_serial", unique = true)
    @Id
    private String productSerial;
    @Column(name = "price", nullable = false)
    Double price;
    @Column(name = "product_title", nullable = false)
    String productTitle;
    @Column(name= "category", nullable = false)
    String category;

}
