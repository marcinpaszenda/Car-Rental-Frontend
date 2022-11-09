package com.carrentalfrontend.client;


import com.carrentalfrontend.domain.Car;
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
public class CarClient {

    private final RestTemplate restTemplate;
    private static final String CAR_URL = "https://car-rental-lemarcar.herokuapp.com/v1/cars/";
    private static CarClient carClient;

    public static CarClient getInstance() {
        if (carClient == null) {
            carClient = new CarClient(new RestTemplate());
        }
        return carClient;
    }

    public List<Car> getAllCars() {
        try {
            ResponseEntity<Car[]> responseEntity = restTemplate.getForEntity(CAR_URL, Car[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public Car getCar(Long carId) {
        try {
            return restTemplate.getForObject(CAR_URL + carId, Car.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void saveNewCar(Car car) {
        try {
            restTemplate.postForObject(CAR_URL, car, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateCar(Car car) {
        try {
            restTemplate.put(CAR_URL, car, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteCar(Long carId) {
        try {
            restTemplate.delete(CAR_URL + carId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
