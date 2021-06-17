package com.advertising.sponsoredads.dto;

import com.advertising.sponsoredads.model.Product;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseCampaignDto {
    String campaignTitle;
    Timestamp startDate;
    Double bid;
    String category;
    Set<Product> products;
}
