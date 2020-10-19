package com.gov.location.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value(value = "${geo.code.api.key}")
    private String geoCodeApiKey;

}
