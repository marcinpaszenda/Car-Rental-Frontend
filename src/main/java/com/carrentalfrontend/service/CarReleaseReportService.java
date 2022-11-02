package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarReleaseReportClient;
import com.carrentalfrontend.dto.CarReleaseReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarReleaseReportService {

    private static CarReleaseReportService carReleaseReportService;
    private final CarReleaseReportClient carReleaseReportClient;

    public static CarReleaseReportService getInstance() {
        if (carReleaseReportService == null) {
            carReleaseReportService = new CarReleaseReportService(CarReleaseReportClient.getInstance());
        }
        return carReleaseReportService;
    }

    public List<CarReleaseReportDto> getAllCarReleaseReports() {
        return carReleaseReportClient.getAllCarReleaseReport();
    }

    public void saveNewCarReleaseReport(CarReleaseReportDto carReleaseReportDto) {
        carReleaseReportClient.saveNewCarReleaseReport(carReleaseReportDto);
    }

    public void updateCarReleaseReport(CarReleaseReportDto carReleaseReportDto) {
        carReleaseReportClient.updateCarReleaseReport(carReleaseReportDto);
    }

    public void deleteCarReleaseReport(Long carReleaseReportId) {
        carReleaseReportClient.deleteCarReleaseReport(carReleaseReportId);
    }
}
