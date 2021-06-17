package com.advertising.sponsoredads.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class Campaign {
    @Column(name = "campaign_title", unique = true)
    @Id
    String campaignTitle;
    @Column(name = "startDate", nullable = false)
    Timestamp startDate;
    @Column(name = "bid", nullable = false)
    Double bid;
    @Column(name = "category", nullable = false)
    String category;

    @JoinTable(name = "campaign_product",
        joinColumns = @JoinColumn(name = "campaign_title"),
        inverseJoinColumns = @JoinColumn(name = "product_serial"))
    @ManyToMany
    private Set<Product> products = new HashSet<>();

}
