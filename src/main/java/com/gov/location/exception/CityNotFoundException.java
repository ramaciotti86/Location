package com.gov.location.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message) {
        super(message);
    }
}
