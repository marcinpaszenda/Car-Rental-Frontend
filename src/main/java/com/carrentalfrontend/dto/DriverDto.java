package com.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private Long driverId;
    private String driverName;
    private String identityCardNumber;
    private String drivingLicenseNumber;
    private Long phoneNumber;
}
