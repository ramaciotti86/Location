package com.gov.location.util;

import com.gov.location.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("harvesineImpl")
public class HarvesineImpl implements SphericalGeometry {

    public double calculation(double cityLat, double cityLon, User user, Double earthRadiusSelected) {
        double userLatitude = user.getLatitude();
        double userLongitude = user.getLongitude();
        double dLat = Math.toRadians(userLatitude - cityLat);
        double dLon = Math.toRadians(userLongitude - cityLon);

        cityLat = Math.toRadians(cityLat);
        userLatitude = Math.toRadians(userLatitude);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(cityLat) * Math.cos(userLatitude);
        double c = 2 * Math.asin(Math.sqrt(a));

        double returnDouble = earthRadiusSelected * c;

        return returnDouble;
    }
}
