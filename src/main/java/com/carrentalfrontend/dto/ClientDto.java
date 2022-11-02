package com.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long clientId;
    private String name;
    private String street;
    private Long streetNumber;
    private String postalCode;
    private String city;
    private String country;
    private String placeOfBirth;
//    private TypeOfIdentificationNumber typeOfIdentificationNumber;
    private Long identificationNumber;
    private Long phoneNumber;
    private String email;
    private List<DriverDto> drivers;
    private List<CarRentDto> carRents;
}
