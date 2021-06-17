package com.advertising.sponsoredads.service;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;
import com.advertising.sponsoredads.dto.ResponseCampaignDto;


public interface CampaignService {
    ResponseCampaignDto createCampaign(CampaignDto campaignDto);
    ProductDto getPromotedProduct(String category);
}
