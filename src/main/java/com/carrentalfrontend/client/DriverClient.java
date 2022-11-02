package com.carrentalfrontend.client;

import com.carrentalfrontend.dto.DriverDto;
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

    public List<DriverDto> getAllDrivers() {
        try {
            ResponseEntity<DriverDto[]> responseEntity = restTemplate.getForEntity(DRIVER_URL, DriverDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewDriver(DriverDto driverDto) {
        try {
            restTemplate.postForObject(DRIVER_URL, driverDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateDriver(DriverDto driverDto) {
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
