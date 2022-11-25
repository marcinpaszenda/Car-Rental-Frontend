package com.carrentalfrontend.client;

import com.carrentalfrontend.domain.CarRent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarRentClient {

    private final RestTemplate restTemplate;
//    private static final String CAR_RENT_URL = "https://car-rental-lemarcar.herokuapp.com/v1/carRents/";
    private static final String CAR_RENT_URL = "http://localhost:8888/v1/carRents/";

    private static CarRentClient carRentClient;

    public static CarRentClient getInstance() {
        if (carRentClient == null) {
            carRentClient = new CarRentClient(new RestTemplate());
        }
        return carRentClient;
    }


    public List<CarRent> getAllCarRents() {

        try {
            ResponseEntity<CarRent[]> responseEntity = restTemplate.getForEntity(CAR_RENT_URL, CarRent[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CarRent getCarRent(Long carRentId) {
        try {
            return restTemplate.getForObject(CAR_RENT_URL + carRentId, CarRent.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void saveNewCarRent(CarRent carRent) {
        try {
            restTemplate.postForObject(CAR_RENT_URL, carRent, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateCarRent(CarRent carRent) {
        try {
            restTemplate.put(CAR_RENT_URL, carRent, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteCarRent(Long carRentId) {
        try {
            restTemplate.delete(CAR_RENT_URL + carRentId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

}
