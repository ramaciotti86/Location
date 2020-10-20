package com.gov.location.util;

import com.gov.location.model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class HaversineImplTest {

    private HarvesineImpl harvesine = new HarvesineImpl();

    static HaversineTestObj[] haversineTestObjs(){
            return new HaversineTestObj[] {new HaversineTestObj(51.507359, -0.136439, User.builder().latitude(51.6553959).longitude(0.0572553).build(), Util.EARTH_RADIUS_MILES, 13.197280362729167),
                    new HaversineTestObj(51.507359, -0.136439, User.builder().latitude(-6.5115909).longitude(105.652983).build(), Util.EARTH_RADIUS_MILES, 7255.580287707475),
                    //North pole and South pole with same meridian (London Longitude) should be 12430M approx
                    new HaversineTestObj(90.0, 0.0, User.builder().latitude(-90.0).longitude(0.0).build(), Util.EARTH_RADIUS_MILES, 12450.731730373185)};
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("haversineTestObjs")
    public void haversineTest(HaversineTestObj haversineTestObj) {
        Assertions.assertEquals(haversineTestObj.expectedReturn, harvesine.calculation(haversineTestObj.getCityLat(), haversineTestObj.getCityLon(), haversineTestObj.getUser(), haversineTestObj.getEarthRadiusSelected()));
    }
}
