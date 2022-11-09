package com.carrentalfrontend.client;

import com.carrentalfrontend.domain.CarReleaseReport;
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
public class CarReleaseReportClient {

    private final RestTemplate restTemplate;
    private static final String CAR_RELEASE_REPORT_URL = "https://car-rental-lemarcar.herokuapp.com/v1/carReleaseReport/";
    private static  CarReleaseReportClient carReleaseReportClient;

    public static CarReleaseReportClient getInstance() {
        if (carReleaseReportClient == null) {
            carReleaseReportClient = new CarReleaseReportClient(new RestTemplate());
        }
        return carReleaseReportClient;
    }

    public List<CarReleaseReport> getAllCarReleaseReport() {
        try {
            ResponseEntity<CarReleaseReport[]> responseEntity = restTemplate.getForEntity(CAR_RELEASE_REPORT_URL, CarReleaseReport[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewCarReleaseReport(CarReleaseReport carReleaseReport) {
        try {
            restTemplate.postForObject(CAR_RELEASE_REPORT_URL, carReleaseReport, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateCarReleaseReport(CarReleaseReport carReleaseReport) {
        try {
            restTemplate.put(CAR_RELEASE_REPORT_URL, carReleaseReport, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteCarReleaseReport(Long carReleaseReportId) {
        try {
            restTemplate.delete(CAR_RELEASE_REPORT_URL + carReleaseReportId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}
