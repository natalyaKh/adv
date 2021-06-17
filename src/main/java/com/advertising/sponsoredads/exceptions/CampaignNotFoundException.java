package com.advertising.sponsoredads.exceptions;

import org.springframework.http.HttpStatus;

public class CampaignNotFoundException extends  RuntimeException{
    public CampaignNotFoundException(String message, HttpStatus status)
    {
        super(message);
    }
}
