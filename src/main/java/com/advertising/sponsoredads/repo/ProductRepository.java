package com.advertising.sponsoredads.repo;

import com.advertising.sponsoredads.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}
