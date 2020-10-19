package com.gov.location.util;

import com.gov.location.model.User;

public interface SphericalGeometry {

    double calculation(double londonLat, double londonLon, User user, Double earthRadiusSelected);
}
