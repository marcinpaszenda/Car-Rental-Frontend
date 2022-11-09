package com.carrentalfrontend.service;

import com.carrentalfrontend.client.DriverClient;
import com.carrentalfrontend.domain.Driver;
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

    public List<Driver> getAllDrivers() {
        return driverClient.getAllDrivers();
    }

    public void saveNewDriver(Driver driver) {
        driverClient.saveNewDriver(driver);
    }

    public void updateDriver(Driver driver) {
        driverClient.updateDriver(driver);
    }

    public void deleteDriver(Long driverId) {
        driverClient.deleteDriver(driverId);
    }
}
