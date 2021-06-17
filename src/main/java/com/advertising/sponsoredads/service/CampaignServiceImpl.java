package com.advertising.sponsoredads.service;

import com.advertising.sponsoredads.dto.CampaignDto;
import com.advertising.sponsoredads.dto.ProductDto;
import com.advertising.sponsoredads.dto.ResponseCampaignDto;
import com.advertising.sponsoredads.exceptions.CampaignNotFoundException;
import com.advertising.sponsoredads.exceptions.InvalidCampaignNameException;
import com.advertising.sponsoredads.model.Campaign;
import com.advertising.sponsoredads.model.Product;
import com.advertising.sponsoredads.repo.CampaignRepository;
import com.advertising.sponsoredads.repo.ProductRepository;
import com.advertising.sponsoredads.utils.ProductCreator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private static final Logger LOGGER = LogManager.getLogger(CampaignServiceImpl.class);
    final ModelMapper mapper = new ModelMapper();
    final LocalDateTime TODAY = LocalDateTime.now();
    final CampaignRepository campaignRepository;
    final ProductRepository productRepository;
    final ProductCreator productCreator;

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

    @Override
    public ProductDto getPromotedProduct(String category) {
        Timestamp dateFrom = Timestamp.valueOf(TODAY.minusDays(9));
        Timestamp dateTo = Timestamp.valueOf(TODAY.plusDays(9));

        Optional<Campaign> optionalCampaign =
            campaignRepository.getCampaignByCategoryAndDate(category, dateFrom, dateTo);
        Product product;
        if(optionalCampaign.isEmpty()){
             product = findHighestBidProductWithNotWorkedCampaign(dateFrom, dateTo);
        }else{
            product = findHighestBidProductFromWorkCampaign(optionalCampaign.get().getProducts());
        }
        return mapper.map(product, ProductDto.class);
    }

    private Product findHighestBidProductFromWorkCampaign(Set<Product> products) {
        Product product = products.iterator().next();
        LOGGER.info("Return product with highest bid for the matching category");
        return product;
    }

    private Product findHighestBidProductWithNotWorkedCampaign(Timestamp dateFrom, Timestamp dateTo) {
        Optional<Campaign> optionalCampaign =
            campaignRepository.getCampaignByDate(dateFrom, dateTo);
        if(optionalCampaign.isEmpty()) {
            LOGGER.info("Worked Campaign not found for date: from " + dateFrom
                + " to " + dateTo);
            throw new CampaignNotFoundException("Worked Campaign not found for date: from " + dateFrom
                + " to " + dateTo, HttpStatus.NOT_FOUND);
        }
        Product product = findHighestBidProductFromWorkCampaign(optionalCampaign.get().getProducts());
        LOGGER.info("Worked Campaign by specific category not found. Return product with highest bid ");
        return product;
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
        LOGGER.info("List of products saved in DB");
        return products;
    }
}
