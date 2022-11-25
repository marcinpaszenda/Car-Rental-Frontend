package com.carrentalfrontend.domain;

import com.carrentalfrontend.domain.enums.TypeOfIdentificationNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long clientId;
    private String name;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String country;
    private String placeOfBirth;
    private TypeOfIdentificationNumber typeOfIdentificationNumber;
    private String identificationNumber;
    private String identityCardNumber;
    private String drivingLicenseNumber;
    private String phoneNumber;
    private String email;
    private List<Driver> drivers;
    private List<CarRent> carRents;
}

