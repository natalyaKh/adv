package com.advertising.sponsoredads.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCampaignNameException extends  RuntimeException{
    public InvalidCampaignNameException(String message, HttpStatus status)
    {
        super(message);
    }
}
