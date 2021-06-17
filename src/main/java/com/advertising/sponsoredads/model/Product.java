package com.advertising.sponsoredads.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

//    @JoinTable(name = "campaign_product",
//        joinColumns = @JoinColumn(name = "product_serial"),
//        inverseJoinColumns = @JoinColumn(name = "campaign_title"))
//    @ManyToMany(cascade = CascadeType.ALL)
//    @ManyToMany(mappedBy = "products")
//    private Set<Campaign> campaigns;


}
