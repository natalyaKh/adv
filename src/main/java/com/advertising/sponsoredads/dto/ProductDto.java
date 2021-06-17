package com.advertising.sponsoredads.dto;

import lombok.*;

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
