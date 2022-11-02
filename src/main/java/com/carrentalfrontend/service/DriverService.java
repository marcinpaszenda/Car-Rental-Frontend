package com.carrentalfrontend.service;

import com.carrentalfrontend.client.DriverClient;
import com.carrentalfrontend.dto.DriverDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private static DriverService driverService;
    private final DriverClient driverClient;

    public static DriverService getInstance() {
        if (driverService == null) {
            driverService = new DriverService(DriverClient.getInstance());
        }
        return driverService;
    }

    public List<DriverDto> getAllDrivers() {
        return driverClient.getAllDrivers();
    }

    public void saveNewDriver(DriverDto driverDto) {
        driverClient.saveNewDriver(driverDto);
    }

    public void updateDriver(DriverDto driverDto) {
        driverClient.updateDriver(driverDto);
    }

    public void deleteDriver(Long driverId) {
        driverClient.deleteDriver(driverId);
    }
}
