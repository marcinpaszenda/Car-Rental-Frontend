package com.carrentalfrontend.service;


import com.carrentalfrontend.client.CarClient;
import com.carrentalfrontend.domain.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private static CarService carService;
    private final CarClient carClient;

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(CarClient.getInstance());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return carClient.getAllCars();
    }

    public Car getCar(Long carId) {
        return carClient.getCar(carId);
    }

    public void saveNewCar(Car car) {
        carClient.saveNewCar(car);
    }

    public void updateCar(Car car) {
        carClient.updateCar(car);
    }

    public void deleteCar(Long carId) {
        carClient.deleteCar(carId);
    }


}
