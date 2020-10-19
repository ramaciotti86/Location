package com.gov.location.service;

import com.gov.location.config.AppConfig;
import com.gov.location.exception.CityNotFoundException;
import com.gov.location.integration.BpdtsIntegration;
import com.gov.location.integration.GeoCodeHereApiIntegration;
import com.gov.location.model.Geocode;
import com.gov.location.model.GeocodeItem;
import com.gov.location.model.GeocodePosition;
import com.gov.location.model.User;
import com.gov.location.util.HaversineManager;
import com.gov.location.TestUtility;
import com.gov.location.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(value = GeoCodeService.class)
public class GeoCodeServiceTest {

    @MockBean
    private GeoCodeHereApiIntegration geoCodeClient;

    @MockBean
    private BpdtsIntegration bpdtsClient;

    @MockBean
    private HaversineManager caclImpl;

    @MockBean
    private AppConfig appConfig;

    @MockBean
    private Util util;

    @Test
    public void getGeocodeByCityAndDistanceValid() throws IOException {
        //Given
        String city = "London";
        Double distance = 50.0;
        String unit = "M";
        GeocodePosition geocodePosition = new GeocodePosition();
        geocodePosition.setLat(51.50643);
        geocodePosition.setLng(-0.12719);
        GeocodeItem geocodeItem = new GeocodeItem();
        geocodeItem.setPosition(geocodePosition);
        Geocode cityGeocode = new Geocode();
        cityGeocode.setItems(Arrays.asList(geocodeItem));
        TestUtility testUtility = new TestUtility();
        GeoCodeService geoCodeService = new GeoCodeService(geoCodeClient, bpdtsClient, appConfig, util, caclImpl);

        //When
        doReturn(testUtility.getExpectedReturn()).when(bpdtsClient).getUsersFromApi();
        doReturn("3DWoA62UFtwpYL1k3YNf3-o19jADI-ijKWC6Ic5MhVE").when(appConfig).getGeoCodeApiKey();
        doReturn(cityGeocode).when(geoCodeClient).getGeocodeByCity(anyString(), anyString());
        doReturn(3963.191).when(util).validateUnit(unit);

        List<User> userList = geoCodeService.getGeocodeByCityAndDistance(city, distance);

        //Then
        Assertions.assertIterableEquals(userList, testUtility.getExpectedReturn());
    }

    @Test
    public void getGeocodeByCityAndDistanceNotFoundException() throws IOException {
        //Given
        String city = "London";
        Double distance = 50.0;
        Geocode cityGeocode = null;
        TestUtility testUtility = new TestUtility();

        GeoCodeService geoCodeService = new GeoCodeService(geoCodeClient, bpdtsClient, appConfig, util, caclImpl);

        //When
        doReturn(testUtility.getExpectedReturn()).when(bpdtsClient).getUsersFromApi();
        doReturn(cityGeocode).when(geoCodeClient).getGeocodeByCity(anyString(), anyString());

        //Then
        Assertions.assertThrows(CityNotFoundException.class, () -> {
            List<User> userList = geoCodeService.getGeocodeByCityAndDistance(city, distance);
        });
    }

    @Test
    public void getGeocodeByCityValid() throws IOException {
        //Given
        String city = "London";
        TestUtility testUtility = new TestUtility();
        GeoCodeService geoCodeService = new GeoCodeService(geoCodeClient, bpdtsClient, appConfig, util, caclImpl);

        //When
        doReturn(testUtility.getExpectedReturnByCity()).when(bpdtsClient).getUsersFromApiByCity(city);
        doReturn(city).when(util).validateCity(city);

        List<User> userList = geoCodeService.getGeocodeByCity(city);

        //Then
        Assertions.assertIterableEquals(userList, testUtility.getExpectedReturnByCity());
    }
}
