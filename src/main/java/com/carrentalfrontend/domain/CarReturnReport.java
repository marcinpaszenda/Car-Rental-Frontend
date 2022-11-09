package com.carrentalfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarReturnReport {

    private Long carReturnReportId;
    private boolean cleanCarBody;
    private boolean cleanCarInterior;
    private int amountOfFuel;
    private Long carMileage;
    private String newCarDamage;
    private String remarks;
    private BigDecimal depositRefund;
}

