package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarReturnReportClient;
import com.carrentalfrontend.dto.CarReturnReportDto;
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

    public List<CarReturnReportDto> getAllCarReturnReports() {
        return carReturnReportClient.getAllCarReturnReports();
    }

    public void  saveNewCarReturnReport(CarReturnReportDto carReturnReportDto) {
        carReturnReportClient.saveNewCarReturnReport(carReturnReportDto);
    }

    public void updateCarReturnReport(CarReturnReportDto carReturnReportDto) {
        carReturnReportClient.updateCarReturnReport(carReturnReportDto);
    }

    public void deleteCArReturnReport(Long carReturnReportId) {
        carReturnReportClient.deleteCarReturnReport(carReturnReportId);
    }
}
