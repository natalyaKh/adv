package com.advertising.sponsoredads.service;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;

public interface CampaignService {
    CampaignDto createCampaign(CampaignDto campaignDto);

    ProductDto getPromotedProduct(String category);
}
