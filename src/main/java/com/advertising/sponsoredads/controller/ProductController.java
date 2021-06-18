package com.advertising.sponsoredads.controller;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;
import com.advertising.sponsoredads.dto.ResponseCampaignDto;
import com.advertising.sponsoredads.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class ProductController {

    final CampaignService campaignService;

    /**
    * API for creating at a adv campaign
     * @return Campaign with list of all promoted product of campaign
     * @throws {@link HttpStatus code 409}
     */
    @PostMapping()
    public ResponseEntity createCampaign(@Valid @RequestBody CampaignDto campaignDto) {
        ResponseCampaignDto rez = campaignService.createCampaign(campaignDto);
        return new ResponseEntity(rez, HttpStatus.CREATED);
    }


    /**
     *the api should return a single promoted product, the one with the highest bid,
     * belonging to active campaign/s from the specified category.
     * If there are no promoted product for the matching category simply return
     * a promoted product with the highest bid.
     *
     * @throws {@link HttpStatus} code 404 - Campaign with specific Campaign title not found in DB
     */
    @GetMapping("/{category}")
    public ResponseEntity serveAd(@PathVariable String category) {
        ProductDto rez = campaignService.getPromotedProduct(category);
        return new ResponseEntity(rez, HttpStatus.OK);
    }
}
