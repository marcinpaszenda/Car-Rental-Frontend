package com.carrentalfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long carId;
    private String carBrand;
    private String registrationNumber;
    private Long carMileage;
    private String vinNumber;
    private String carDamage;

}
