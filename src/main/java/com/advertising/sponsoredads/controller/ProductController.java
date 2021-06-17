package com.advertising.sponsoredads.controller;

import com.advertising.sponsoredads.model.Campaign;
import com.advertising.sponsoredads.model.Product;
import com.advertising.sponsoredads.repo.CampaignRepository;
import com.advertising.sponsoredads.repo.ProductRepository;
import com.advertising.sponsoredads.utils.ProductCreator;
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
    @Autowired
    ProductCreator productCreator;
    @GetMapping()
    public String createProduct(){
//        Set<Product> products = productCreator.createProduct("PHONE");
        Set<Product> products = productCreator.createProduct("EAT");
        System.err.println(products);

        productRepository.saveAll(products);
        Campaign  campaigns = new Campaign();
        campaigns.setBid(9.9);
        campaigns.setCampaignTitle("camp1");
        campaigns.setCategory("phone");
        campaigns.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
        campaigns.setProducts(products);
        campaignRepository.save(campaigns);
        return "Sucesssful";

    }
}
