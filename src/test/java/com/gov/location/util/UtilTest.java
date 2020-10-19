package com.gov.location.util;

import com.gov.location.NullableConverter;
import com.gov.location.exception.CityNotFoundException;
import com.gov.location.exception.UnitNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class UtilTest {

    @ParameterizedTest(name = "{0} should return {1}")
    @CsvSource({"M, 3963.191", "null, 3963.191", "'', 3963.191", "K, 6378.137"})
    void validateUnitTest(@ConvertWith(NullableConverter.class) String unit, Double expectedResult){
        //Given
        Util util = new Util();
        Double radius = util.validateUnit(unit);

        //Then
        Assertions.assertEquals(radius, expectedResult);
    }

    @Test
    public void validateInvalidUnitAbbreviationTest(){
        //Given
        Util util = new Util();

        Assertions.assertThrows(UnitNotFoundException.class, () -> {
            Double radius = util.validateUnit("C");
        });
    }

    @Test
    public void validateCityTest(){
        //Given
        String city = "London";
        String expectedResult = city;
        Util util = new Util();
        String returnedCity = util.validateCity(city);

        //Then
        Assertions.assertEquals(returnedCity, expectedResult);
    }

    @ParameterizedTest(name = "{0} should return CityNotFoundException")
    @NullAndEmptySource
    public void validateInvalidCityTest(@ConvertWith(NullableConverter.class) String city){
        //Given
        Util util = new Util();

        Assertions.assertThrows(CityNotFoundException.class, () -> {
            String cityReturn = util.validateCity(city);
        });
    }
}
