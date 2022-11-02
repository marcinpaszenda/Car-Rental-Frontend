package com.carrentalfrontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRentDto {

    private Long carRentId;
    private LocalDate rentalDate;
    private LocalTime rentalHour;
    private LocalDate returnDate;
    private LocalTime returnHour;
    private Long rentalDayLength;
    private Currency currency;
    private BigDecimal dailyRate;
    private BigDecimal additionalCosts;
    private BigDecimal deposit;
    private BigDecimal totalCost;
    private boolean dailyMileageLimit;
    private boolean travelAbroad;
    private boolean registrationCertificate;
    private boolean abolitionDeductibleInDamage;
    private BigDecimal abolitionFee;


    private CarDto carDto;
    private DriverDto driverDto;
    private ClientDto clientDto;
    private CarReleaseReportDto carReleaseReportDto;
    private CarReturnReportDto carReturnReportDto;


}
