CREATE TABLE IF NOT EXISTS campaign(
    campaign_title VARCHAR(100) PRIMARY KEY,
    bid DOUBLE NOT NULL,
    startDate TIMESTAMP,
    category VARCHAR(100)
)

