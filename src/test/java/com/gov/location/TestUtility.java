package com.gov.location;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gov.location.model.User;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TestUtility {

    public List<User> getExpectedReturn() throws IOException {

        @Cleanup FileInputStream fis = new FileInputStream("src/test/resources/usersByDistance.json");
        String jsonExpected = IOUtils.toString(fis, "UTF-8");

        return new Gson().fromJson(jsonExpected, new TypeToken<List<User>>() {}.getType());
    }

    public List<User> getExpectedReturnByCity() throws IOException {

        @Cleanup FileInputStream fis = new FileInputStream("src/test/resources/usersByLondon.json");
        String jsonExpected = IOUtils.toString(fis, "UTF-8");

        return new Gson().fromJson(jsonExpected, new TypeToken<List<User>>() {}.getType());
    }

}
