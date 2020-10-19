package com.gov.location.model;

import lombok.Data;

import java.util.List;

@Data
public class Geocode {

    private List<GeocodeItem> items;

}
