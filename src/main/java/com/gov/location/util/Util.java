package com.gov.location.util;

import com.gov.location.exception.CityNotFoundException;
import com.gov.location.exception.UnitNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Util {

    public static final Double EARTH_RADIUS_MILES = 3963.191;

    public static final String UNIT_MILES = "M";

    public static final Double EARTH_RADIUS_KILOMETERS = 6378.137;

    public static final String UNIT_KILOMETERS = "K";

    public Double validateUnit(String unit) {
        Double EARTH_RADIUS_SELECTED;
        if(StringUtils.isEmpty(unit) || unit.equals(UNIT_MILES)){
            EARTH_RADIUS_SELECTED = EARTH_RADIUS_MILES;
        }else if (unit.equals(UNIT_KILOMETERS)){
            EARTH_RADIUS_SELECTED = EARTH_RADIUS_KILOMETERS;
        }else {
            throw new UnitNotFoundException("Unit: " + unit);
        }
        return EARTH_RADIUS_SELECTED;
    }

    public String validateCity(String city) {
        if(StringUtils.isEmpty(city)){
            throw new CityNotFoundException("City: " + city);
        } else{
            return city;
        }
    }
}
