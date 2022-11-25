package com.carrentalfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    private Long driverId;
    private String driverName;
    private String identityCardNumber;
    private String drivingLicenseNumber;
    private String phoneNumber;
    private Client client;
    private List<CarRent> carRents;

}
