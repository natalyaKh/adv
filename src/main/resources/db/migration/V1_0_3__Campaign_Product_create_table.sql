CREATE TABLE IF NOT EXISTS product_campaign(
    product_serial VARCHAR(100) NOT NULL,
    campaign_title VARCHAR(100) NOT NULL,
    FOREIGN KEY (product_serial) REFERENCES product (product_serial) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (campaign_title) REFERENCES campaign (campaign_title) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (product_serial, campaign_title)
)


