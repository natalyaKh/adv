package com.advertising.sponsoredads.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CampaignDto {
    @NotNull(message = "Title of campaign cannot be null")
    String campaignTitle;
    @NotNull(message = "Date of starting of campaign cannot be null")
    Timestamp startDate;
    @Min(value = 0, message = "bid should not be less than 0")
    Double bid;
    @NotNull(message = "Category of campaign cannot be null")
    String category;
}
