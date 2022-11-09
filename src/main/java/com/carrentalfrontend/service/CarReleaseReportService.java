package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarReleaseReportClient;
import com.carrentalfrontend.domain.CarReleaseReport;
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

    public List<CarReleaseReport> getAllCarReleaseReports() {
        return carReleaseReportClient.getAllCarReleaseReport();
    }

    public void saveNewCarReleaseReport(CarReleaseReport carReleaseReport) {
        carReleaseReportClient.saveNewCarReleaseReport(carReleaseReport);
    }

    public void updateCarReleaseReport(CarReleaseReport carReleaseReport) {
        carReleaseReportClient.updateCarReleaseReport(carReleaseReport);
    }

    public void deleteCarReleaseReport(Long carReleaseReportId) {
        carReleaseReportClient.deleteCarReleaseReport(carReleaseReportId);
    }
}
