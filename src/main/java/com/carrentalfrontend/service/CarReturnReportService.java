package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarReturnReportClient;
import com.carrentalfrontend.domain.CarReturnReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarReturnReportService {

    public static CarReturnReportService carReturnReportService;
    private final CarReturnReportClient carReturnReportClient;

    public static CarReturnReportService getInstance() {
        if (carReturnReportService == null) {
            carReturnReportService = new CarReturnReportService(CarReturnReportClient.getInstance());
        }
        return carReturnReportService;
    }

    public List<CarReturnReport> getAllCarReturnReports() {
        return carReturnReportClient.getAllCarReturnReports();
    }

    public void  saveNewCarReturnReport(CarReturnReport carReturnReport) {
        carReturnReportClient.saveNewCarReturnReport(carReturnReport);
    }

    public void updateCarReturnReport(CarReturnReport carReturnReport) {
        carReturnReportClient.updateCarReturnReport(carReturnReport);
    }

    public void deleteCArReturnReport(Long carReturnReportId) {
        carReturnReportClient.deleteCarReturnReport(carReturnReportId);
    }
}
