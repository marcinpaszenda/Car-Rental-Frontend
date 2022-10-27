package com.carrentalfrontend.service;


import com.carrentalfrontend.CarClass;
import com.carrentalfrontend.domain.Car;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CarService {

    private Set<Car> cars;
    private static CarService carService;

    private CarService() {
        this.cars = exampleData();
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    public Set getCars() {
        return new HashSet<>(cars);
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }


    private Set exampleData() {
        Set cars = new HashSet<>();
        cars.add(new Car("Audi A4", "SK3445R", 23500L, "VIN33225RF667J", CarClass.C));
        cars.add(new Car("Skoda Fabia", "SR35960", 44500L, "VIN3VEFV444", CarClass.B));
        cars.add(new Car("Toyota Corolla", "SK110D4", 102334L, "VIN5943N32RJD", CarClass.C));
        cars.add(new Car("Mazda CX3", "SK3445R", 69795L, "VIN40GJ4ROFL", CarClass.D));
        cars.add(new Car("Fiat Tipo", "SI23433", 130495L, "VIN20IJEWKDEFE44", CarClass.A));
        cars.add(new Car("Skoda Octavia", "SK1679P", 60594L, "VIN39595GGD34", CarClass.C));
        cars.add(new Car("Volkswagen Golf", "SK22Q5T", 39382L, "VIN90980FJRF", CarClass.C));
        cars.add(new Car("Mercedes GLE", "SR9000A", 9210L, "VIN86500FDD3", CarClass.E));
        cars.add(new Car("Toyota Yaris", "SK70192", 11230L, "VIN12KS4456T", CarClass.A));
        cars.add(new Car("Renault Megane", "SR55C44", 88594L, "VIN0499VJIRJE", CarClass.C));
        cars.add(new Car("BMW 5", "SK098PL", 69504L, "VIN064G4KFG3332", CarClass.E));
        cars.add(new Car("Audi A4", "SWD4410", 22039L, "VIN5OVNF3O302", CarClass.C));
        return cars;
    }

    public Set findByCarBrand(String carBrand) {
        return cars.stream().filter(car -> car.getCarBrand().contains(carBrand))
                .collect(Collectors.toSet());
    }

    public void save(Car car) {
        this.cars.add(car);
    }

    public void delete(Car car) {
        this.cars.remove(car);
    }

}
