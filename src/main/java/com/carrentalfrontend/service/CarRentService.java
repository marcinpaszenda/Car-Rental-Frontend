package com.carrentalfrontend.service;

import com.carrentalfrontend.client.CarRentClient;
import com.carrentalfrontend.domain.CarRent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<CarRent> getAllActiveCarRents() {
        return carRentClient.getAllCarRents()
                .stream()
                .filter(carRent -> carRent.getReturnDate() == null || carRent.getReturnDate().isAfter(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());
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
                .filter(carRent -> carRent.getCar().getRegistrationNumber().toLowerCase().contains(registrationNumber) ||
                        carRent.getCar().getRegistrationNumber().toUpperCase().contains(registrationNumber))
                .collect(Collectors.toList()));
    }

    public List<CarRent> findByCarModel(String car) {
        return (carRentClient.getAllCarRents().stream()
                .filter(carRent -> carRent.getCar().getCar().toUpperCase().contains(car) ||
                        carRent.getCar().getCar().toLowerCase().contains(car))
                .collect(Collectors.toList()));
    }

    public List<CarRent> findByClient(String client) {
        return (carRentClient.getAllCarRents().stream()
                .filter(carRent -> carRent.getClient().getName().toUpperCase().contains(client) ||
                        carRent.getClient().getName().toLowerCase().contains(client))
                .collect(Collectors.toList()));
    }
}
