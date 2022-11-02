package com.carrentalfrontend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarReleaseReportDto {

    private Long carReleaseReportId;
    private boolean cleanCarBody;
    private boolean cleanCarInterior;
    private int amountOfFuel;
    private Long carMileage;
    private String remarks;
}