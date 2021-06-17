# adv

Exceptions -> 

    - HttpStatus.INTERNAL_SERVER_ERROR - 500
    - HttpStatus.BAD_REQUEST = - 400 Errors of Validation
    - HttpStatus.CONFLICT - 409 - CampaignName not unique


REQUEST JSON For createCampaign API -> 

    {
    "campaignTitle":"SuperPuperCampaign",
    "startDate":"{{currentDate}}",
    "bid":3.6,
    "category":"PHONE"
    }

SELECT * FROM CAMPAIGN ;
SELECT * FROM  CAMPAIGN_PRODUCT ;
SELECT * FROM PRODUCT 
