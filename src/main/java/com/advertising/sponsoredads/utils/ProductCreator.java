package com.advertising.sponsoredads.utils;

import com.advertising.sponsoredads.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ProductCreator {
    private static final Logger LOGGER = LogManager.getLogger(ProductCreator.class);
    final ObjectMapper mapper = new ObjectMapper();
    @Value("${file.link:./product.json}")
    String myFilePath;
    Set<Product> products = new HashSet<>();

    /**
     * method for receiving product from json document.
     * I suppose that in real application here should be request to another service.
     */
    @SneakyThrows
    public Set<Product> createProduct(String category) {
        FileReader reader = new FileReader(myFilePath);
        products = mapper.readValue(reader, new TypeReference<>() {});
        LOGGER.info("Got product from file ");
       return products.stream().filter(p->p.getCategory().equals(category)).collect(Collectors.toSet());
    }
}



