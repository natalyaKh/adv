package com.advertising.sponsoredads.repo;

import com.advertising.sponsoredads.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
}
