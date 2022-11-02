package com.carrentalfrontend.client;

import com.carrentalfrontend.dto.CarReturnReportDto;
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
public class CarReturnReportClient {

    private final RestTemplate restTemplate;
    private static final String CAR_RETURN_REPORT_URL = "https://car-rental-lemarcar.herokuapp.com/v1/carReturnReport/";
    private static CarReturnReportClient carReturnReportClient;

    public static CarReturnReportClient getInstance() {
        if (carReturnReportClient == null) {
            carReturnReportClient = new CarReturnReportClient(new RestTemplate());
        }
        return carReturnReportClient;
    }

    public List<CarReturnReportDto> getAllCarReturnReports() {
        try {
            ResponseEntity<CarReturnReportDto[]> responseEntity = restTemplate.getForEntity(CAR_RETURN_REPORT_URL, CarReturnReportDto[].class);
            return Arrays.asList((Objects.requireNonNull(responseEntity.getBody())));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void saveNewCarReturnReport(CarReturnReportDto carReturnReportDto) {
        try {
            restTemplate.postForObject(CAR_RETURN_REPORT_URL, carReturnReportDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
    public void updateCarReturnReport(CarReturnReportDto carReturnReportDto) {
        try {
            restTemplate.put(CAR_RETURN_REPORT_URL, carReturnReportDto, Void.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteCarReturnReport(Long carReturnReportId) {
        try {
            restTemplate.delete(CAR_RETURN_REPORT_URL + carReturnReportId);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }
    }
}