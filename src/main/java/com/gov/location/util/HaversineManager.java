package com.gov.location.util;

import com.gov.location.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HaversineManager {

    private final SphericalGeometry sphericalGeometry;

    public HaversineManager(@Qualifier("harvesineImpl") SphericalGeometry sphericalGeometry){
        this.sphericalGeometry = sphericalGeometry;
    }

    public double calculation(double cityLat, double cityLon, User user, Double earthRadiusSelected){
        return sphericalGeometry.calculation(cityLat, cityLon, user, earthRadiusSelected);
    }
}
