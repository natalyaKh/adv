CREATE TABLE IF NOT EXISTS campaign(
    campaign_title VARCHAR(100) PRIMARY KEY,
    bid DOUBLE NOT NULL,
    start_date TIMESTAMP,
    category VARCHAR(100)
)

