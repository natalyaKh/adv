package com.advertising.sponsoredads.dto;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    String productSerial;
    Double price;
    String productTitle;
    String category;
}
