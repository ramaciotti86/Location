package com.gov.location.controller;

import com.gov.location.service.GeoCodeService;
import com.gov.location.TestUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoCodeService geoCodeService;

    @Test
    public void getUsersByCityDistance() throws Exception {
        //Given
        String city = "London";
        Double distance = 50.0;
        TestUtility testUtility = new TestUtility();

        //When
        doReturn(testUtility.getExpectedReturn()).when(geoCodeService).getGeocodeByCityAndDistance(anyString(), anyDouble());

        //Then
        mockMvc.perform(get("/city/{city}/distance/{distance}/users", city, distance))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUsersByCity() throws Exception {
        //Given
        String city = "London";
        TestUtility testUtility = new TestUtility();

        //When
        doReturn(testUtility.getExpectedReturn()).when(geoCodeService).getGeocodeByCity(anyString());

        //Then
        mockMvc.perform(get("/city/{city}/users", city))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
