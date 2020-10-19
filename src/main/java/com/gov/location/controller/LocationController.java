package com.gov.location.controller;

import com.gov.location.model.User;
import com.gov.location.service.GeoCodeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class LocationController {

    private final GeoCodeService geoCodeService;

    public LocationController(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }

    @ApiOperation(value = "Return users whose current coordinates are within 'distance' Miles of chosen city.")
    @RequestMapping(method = RequestMethod.GET, path = "/city/{city}/distance/{distance}/users", name = "getUsersByCityDistance", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUsersByCityDistance(@PathVariable(name = "city") @ApiParam(value = "Name of the city", name = "city", required = true) String city,  @PathVariable(value = "distance") @Min(value = 1, message = "Distance should be a positive number and equal or greater then 1") @ApiParam(value = "Distance in Miles", name = "distance", required = true) Double distance){
        return geoCodeService.getGeocodeByCityAndDistance(city, distance);
    }

    @ApiOperation(value = "Return users living in chosen city.")
    @RequestMapping(method = RequestMethod.GET, path = "/city/{city}/users", name = "getUsersByCity", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUsersByCityDistance(@PathVariable(name = "city") @ApiParam(value = "Name of the city", name = "city", required = true) String city){
        return geoCodeService.getGeocodeByCity(city);
    }
}
