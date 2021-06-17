package com.advertising.sponsoredads.controller;

import com.advertising.sponsoredads.model.Campaign;
import com.advertising.sponsoredads.model.Product;
import com.advertising.sponsoredads.repo.CampaignRepository;
import com.advertising.sponsoredads.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CampaignRepository campaignRepository;

    @GetMapping()
    public String createProduct(){

        Product product = new Product();
        product.setProductSerial(UUID.randomUUID().toString());
        product.setProductTitle("product3");
        product.setPrice(10.0);
        product.setCategory("phone");
        System.err.println(product);

        productRepository.save(product);
        Campaign  campaigns = new Campaign();
        campaigns.setBid(9.9);
        campaigns.setCampaignTitle("camp1");
        campaigns.setCategory("phone");
        campaigns.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        Set<Product> pr = new HashSet<>();
        pr.add(product);
        campaigns.setProducts(pr);
        campaignRepository.save(campaigns);
        return "Sucesssful";

    }
}
