package com.carrentalfrontend.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarReleaseReport {

    private Long carReleaseReportId;
    private boolean cleanCarBody;
    private boolean cleanCarInterior;
    private int amountOfFuel;
    private Long carMileage;
    private String remarks;
}

