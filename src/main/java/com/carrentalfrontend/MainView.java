package com.carrentalfrontend;

import com.carrentalfrontend.dto.CarDto;
import com.carrentalfrontend.dto.CarRentDto;
import com.carrentalfrontend.dto.DriverDto;
import com.carrentalfrontend.service.CarRentService;
import com.carrentalfrontend.service.CarService;
import com.carrentalfrontend.service.DriverService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    Grid<CarRentDto> grid = new Grid<>(CarRentDto.class);
    Grid<DriverDto> driverGrid = new Grid<>(DriverDto.class);
    Grid<CarDto> carGrid = new Grid<>(CarDto.class);

    private CarRentService carRentService = CarRentService.getInstance();
    private DriverService driverService = DriverService.getInstance();
    private CarService carService = CarService.getInstance();

    public MainView() {
        addClassName("car-rents-name");
        setSizeFull();
        configureCarRentGrid();

        add(grid, driverGrid);
        updateList();

    }

    private void configureCarRentGrid() {
        grid.addClassName("car-rental-grid");
        grid.setSizeFull();
        grid.setColumns("rentalDate", "rentalHour", "deposit");

        driverGrid.setColumns("driverName", "drivingLicenseNumber", "phoneNumber");

        grid.addColumn(carRentDto -> {
            CarDto carDto = carRentDto.getCarDto();
            return carDto == null ? "-" : carDto.getCarBrand();
        }).setHeader("Car");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }


    private void updateList() {
        grid.setItems(carRentService.getAllCarRents());
        driverGrid.setItems(driverService.getAllDrivers());

    }

}
