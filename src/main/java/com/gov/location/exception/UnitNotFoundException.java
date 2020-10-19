package com.gov.location.exception;

public class UnitNotFoundException extends RuntimeException {
    public UnitNotFoundException(String unit) {
        super(unit);
    }
}
