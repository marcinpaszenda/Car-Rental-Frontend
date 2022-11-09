package com.carrentalfrontend.client;

import com.carrentalfrontend.domain.Driver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DriverClient {

    private final RestTemplate restTemplate;
    private static final String DRIVER_URL = "https://car-rental-lemarcar.herokuapp.com/v1/drivers/";
    private static DriverClient driverClient;

    public static DriverClient getInstance() {
        if (driverClient == null) {
            driverClient = new DriverClient(new RestTemplate());
        }
        return driverClient;
    }

    public List<Driver> getAllDrivers() {
        try {
            ResponseEntity<Driver[]> responseEntity = restTemplate.getForEntity(DRIVER_URL, Driver[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewDriver(Driver driver) {
        try {
            restTemplate.postForObject(DRIVER_URL, driver, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateDriver(Driver driver) {
        try {
            restTemplate.put(DRIVER_URL, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteDriver(Long driverId) {
        try {
            restTemplate.delete(DRIVER_URL + driverId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
