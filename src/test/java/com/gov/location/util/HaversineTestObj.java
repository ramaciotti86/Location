package com.gov.location.util;

import com.gov.location.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class HaversineTestObj {

    double cityLat;
    double cityLon;
    User user;
    Double earthRadiusSelected;
    Double expectedReturn;

}
