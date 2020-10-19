package com.gov.location.integration;

import com.gov.location.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://bpdts-test-app.herokuapp.com", name = "BpdtsIntegration")
public interface BpdtsIntegration {

    @GetMapping(path = "/users")
    List<User> getUsersFromApi();

    @GetMapping(path = "/city/{city}/users")
    List<User> getUsersFromApiByCity(@PathVariable String city);

}
