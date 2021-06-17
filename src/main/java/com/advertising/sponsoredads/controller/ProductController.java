package com.advertising.sponsoredads.controller;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;
import com.advertising.sponsoredads.dto.ResponseCampaignDto;
import com.advertising.sponsoredads.service.CampaignServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class ProductController {

    final CampaignServiceImpl campaignService;

    public ProductController(CampaignServiceImpl campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping()
    public ResponseEntity createCampaign(@Valid @RequestBody CampaignDto campaignDto) {
        ResponseCampaignDto rez = campaignService.createCampaign(campaignDto);
        return new ResponseEntity(rez, HttpStatus.CREATED);
    }

    @GetMapping("/{category}")
    public ResponseEntity serveAd(@PathVariable String category) {
        ProductDto rez = campaignService.getPromotedProduct(category);
        return new ResponseEntity(rez, HttpStatus.OK);
    }
}
