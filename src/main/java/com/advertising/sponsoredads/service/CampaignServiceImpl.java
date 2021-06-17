package com.advertising.sponsoredads.service;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;
import com.advertising.sponsoredads.dto.ResponseCampaignDto;
import com.advertising.sponsoredads.exceptions.InvalidCampaignNameException;
import com.advertising.sponsoredads.model.Campaign;
import com.advertising.sponsoredads.model.Product;
import com.advertising.sponsoredads.repo.CampaignRepository;
import com.advertising.sponsoredads.repo.ProductRepository;
import com.advertising.sponsoredads.utils.ProductCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CampaignServiceImpl implements CampaignService {
    private static final Logger LOGGER = LogManager.getLogger(CampaignServiceImpl.class);
    ModelMapper mapper = new ModelMapper();

    final CampaignRepository campaignRepository;
    final ProductRepository productRepository;
    final ProductCreator productCreator;

    public CampaignServiceImpl(CampaignRepository campaignRepository, ProductRepository productRepository, ProductCreator productCreator) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
        this.productCreator = productCreator;
    }

    @Override
    public ResponseCampaignDto createCampaign(CampaignDto campaignDto) {
        checkCampaign(campaignDto);
        Campaign campaign = mapper.map(campaignDto, Campaign.class);
        Set<Product> products = getAllProductsByCategory(campaign.getCategory());
        LOGGER.info("Got all products by category " + campaign.getCategory() + " from file");
        campaign.setProducts(products);
        LOGGER.info("Products for Campaign with name " + campaignDto.getCampaignTitle() + "saved in DB");
        Campaign savedCampaign = campaignRepository.save(campaign);
        LOGGER.info("Campaign with name " + campaign.getCampaignTitle() + " ready for start");
        return mapper.map(savedCampaign, ResponseCampaignDto.class);
    }

    private void checkCampaign(CampaignDto campaignDto) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(campaignDto.getCampaignTitle());
        if(optionalCampaign.isPresent()){
            LOGGER.info("Campaign with title + " + campaignDto.getCampaignTitle()
                + " exists in DB");
            throw new InvalidCampaignNameException("Campaign with title + " + campaignDto.getCampaignTitle()
            + " exists in DB", HttpStatus.CONFLICT);
        }
    }

    private Set<Product> getAllProductsByCategory(String category) {
        Set<Product> products = productCreator.createProduct(category);
        productRepository.saveAll(products);
        return products;
    }

    @Override
    public ProductDto getPromotedProduct(String category) {
        // TODO: 17/06/2021
        return null;
    }
}
