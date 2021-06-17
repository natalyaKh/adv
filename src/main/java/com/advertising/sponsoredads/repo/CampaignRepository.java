package com.advertising.sponsoredads.repo;

import com.advertising.sponsoredads.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
    @Query(value = "SELECT * FROM CAMPAIGN WHERE CATEGORY =:category AND START_DATE <:dateTo AND START_DATE >:dateFrom ORDER BY BID DESC LIMIT 1", nativeQuery = true)
    Optional<Campaign> getCampaignByCategoryAndDate(@Param("category") String category,
                                                @Param("dateFrom") Timestamp dateFrom,
                                                @Param("dateTo") Timestamp dateTo);

    @Query(value = "SELECT * FROM CAMPAIGN WHERE START_DATE <:dateTo AND START_DATE >:dateFrom ORDER BY BID DESC LIMIT 1", nativeQuery = true)
    Optional<Campaign> getCampaignByDate( @Param("dateFrom") Timestamp dateFrom,
                                          @Param("dateTo") Timestamp dateTo);
}
