package com.gov.location.integration;

import com.gov.location.model.Geocode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://geocode.search.hereapi.com/v1/geocode", name = "GeoCodeHereApiIntegration")
public interface GeoCodeHereApiIntegration {

    @GetMapping(path = "?q={city}&apiKey={apiKey}")
    Geocode getGeocodeByCity(@PathVariable String city, @PathVariable String apiKey);

}
