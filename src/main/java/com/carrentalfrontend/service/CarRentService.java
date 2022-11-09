package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarRentClient;
import com.carrentalfrontend.domain.CarRent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarRentService {

    private static CarRentService carRentService;
    private final CarRentClient carRentClient;

    public static CarRentService getInstance() {
        if (carRentService == null) {
            carRentService = new CarRentService(CarRentClient.getInstance());
        }
        return carRentService;
    }

    public List<CarRent> getAllCarRents() {
        return carRentClient.getAllCarRents();
    }

    public CarRent getCarRent(Long carRentId) {
        return carRentClient.getCarRent(carRentId);
    }

    public void saveNewCarRent(CarRent carRent) {
        carRentClient.saveNewCarRent(carRent);
    }

    public void updateCarRent(CarRent carRent) {
        carRentClient.updateCarRent(carRent);
    }

    public void deleteCarRent(Long carRentId) {
        carRentClient.deleteCarRent(carRentId);
    }

    public List<CarRent> findByRegistrationNumber(String registrationNumber) {
        return (carRentClient.getAllCarRents().stream()
                .filter(carRent -> carRent.getCar().getRegistrationNumber().contains(registrationNumber))
                .collect(Collectors.toList()));
    }
}
