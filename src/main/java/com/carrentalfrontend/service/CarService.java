package com.carrentalfrontend.service;


import com.carrentalfrontend.client.CarClient;
import com.carrentalfrontend.dto.CarDto;
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

    public List<CarDto> getAllCars() {
        return carClient.getAllCars();
    }

    public CarDto getCar(Long carId) {
        return carClient.getCar(carId);
    }

    public void saveNewCar(CarDto carDto) {
        carClient.saveNewCar(carDto);
    }

    public void updateCar(CarDto carDto) {
        carClient.updateCar(carDto);
    }

    public void deleteCar(Long carId) {
        carClient.deleteCar(carId);
    }

}
