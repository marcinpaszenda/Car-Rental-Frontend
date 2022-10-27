package com.carrentalfrontend.domain;

import com.carrentalfrontend.CarClass;

import java.util.Objects;

public class Car {

    private String carBrand;
    private String registrationNumber;
    private Long carMileage;
    private String vinNumber;
    private CarClass carClass;

    public Car() {
    }

    public Car(String carBrand, String registrationNumber, Long carMileage, String vinNumber, CarClass carClass) {
        this.carBrand = carBrand;
        this.registrationNumber = registrationNumber;
        this.carMileage = carMileage;
        this.vinNumber = vinNumber;
        this.carClass = carClass;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Long getCarMileage() {
        return carMileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!Objects.equals(carBrand, car.carBrand)) return false;
        if (!Objects.equals(registrationNumber, car.registrationNumber))
            return false;
        if (!Objects.equals(carMileage, car.carMileage)) return false;
        if (!Objects.equals(vinNumber, car.vinNumber)) return false;
        return carClass == car.carClass;
    }

    @Override
    public int hashCode() {
        int result = carBrand != null ? carBrand.hashCode() : 0;
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (carMileage != null ? carMileage.hashCode() : 0);
        result = 31 * result + (vinNumber != null ? vinNumber.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        return result;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setCarMileage(Long carMileage) {
        this.carMileage = carMileage;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }
}