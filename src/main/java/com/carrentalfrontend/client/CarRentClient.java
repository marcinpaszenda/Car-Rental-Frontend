package com.carrentalfrontend.client;

import com.carrentalfrontend.dto.CarRentDto;
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
    private static final String CAR_RENT_URL = "https://car-rental-lemarcar.herokuapp.com/v1/carRents/";
    private static CarRentClient carRentClient;

    public static CarRentClient getInstance() {
        if (carRentClient == null) {
            carRentClient = new CarRentClient(new RestTemplate());
        }
        return carRentClient;
    }

    public List<CarRentDto> getAllCarRents() {

        try {
            ResponseEntity<CarRentDto[]> responseEntity = restTemplate.getForEntity(CAR_RENT_URL, CarRentDto[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewCarRent(CarRentDto carRentDto) {
        try {
            restTemplate.postForObject(CAR_RENT_URL, carRentDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateCarRent(CarRentDto carRentDto) {
        try {
            restTemplate.put(CAR_RENT_URL,carRentDto, Void.class);
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
