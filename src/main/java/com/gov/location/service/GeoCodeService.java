package com.gov.location.service;

import com.gov.location.config.AppConfig;
import com.gov.location.exception.CityNotFoundException;
import com.gov.location.integration.BpdtsIntegration;
import com.gov.location.integration.GeoCodeHereApiIntegration;
import com.gov.location.model.Geocode;
import com.gov.location.model.GeocodePosition;
import com.gov.location.model.User;
import com.gov.location.util.HaversineManager;
import com.gov.location.util.Util;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeoCodeService {

    private final GeoCodeHereApiIntegration geoCodeClient;

    private final BpdtsIntegration bpdtsClient;

    private HaversineManager calcImpl;

    private AppConfig appConfig;

    private Util util;

    public GeoCodeService(GeoCodeHereApiIntegration geoCodeClient, BpdtsIntegration bpdtsClient, AppConfig appConfig, Util util, HaversineManager calcImpl) {
        this.geoCodeClient = geoCodeClient;
        this.bpdtsClient = bpdtsClient;
        this.appConfig = appConfig;
        this.util = util;
        this.calcImpl = calcImpl;
    }

    public List<User> getGeocodeByCityAndDistance(String city, Double distance) {
        List<User> usersToReturn = new ArrayList<>();
        List<User> allUsers = bpdtsClient.getUsersFromApi();
        Geocode cityGeocode = geoCodeClient.getGeocodeByCity(city, appConfig.getGeoCodeApiKey());
        if (StringUtils.isEmpty(cityGeocode)){
            throw new CityNotFoundException("City: " + city);
        }
        GeocodePosition cityGeocodePosition = cityGeocode.getItems().get(0).getPosition();

        //Validate Unit passed - If want to change in future from Miles to Kilometers.
        Double EARTH_RADIUS_SELECTED = util.validateUnit(Util.UNIT_MILES);

        for (User user: allUsers) {
            Double totalDistance = calcImpl.calculation(cityGeocodePosition.getLat(), cityGeocodePosition.getLng(), user, EARTH_RADIUS_SELECTED);
            if (totalDistance <= distance){
                usersToReturn.add(user);
            }
        }
        return usersToReturn;
    }

    public List<User> getGeocodeByCity(String city) {
        city  = util.validateCity(city);
        List<User> usersToReturn = bpdtsClient.getUsersFromApiByCity(city);

        return usersToReturn;
    }
}
