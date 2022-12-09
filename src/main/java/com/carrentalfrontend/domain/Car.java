package com.carrentalfrontend.domain;

import com.carrentalfrontend.domain.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long carId;
    private String car;
    private String carBrand;
    private String registrationNumber;
    private Long carMileage;
    private CarStatus carStatus;
    private String carDamage;
}
