package com.carrentalfrontend.views;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.domain.CarRent;
import com.carrentalfrontend.domain.Client;
import com.carrentalfrontend.forms.CarRentalPreview;
import com.carrentalfrontend.service.CarRentService;
import com.carrentalfrontend.service.ClientService;
import com.carrentalfrontend.service.DriverService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "car-rentals-view", layout = MainLayout.class)
public class CarRentalsView extends VerticalLayout {

    private final Grid<CarRent> grid = new Grid<>(CarRent.class);
    private final TextField filterText = new TextField();
    private final TextField filterTextCar = new TextField();
    private final TextField filterTextClient= new TextField();

    private final CarRentService carRentService = CarRentService.getInstance();
    private ClientService clientService = ClientService.getInstance();
    private DriverService driverService = DriverService.getInstance();

    private CarRentalPreview carRentalPreview = new CarRentalPreview(this);


    public CarRentalsView() {
        addClassName("car-rentals-view");
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        update();
        updateByCarModel();
        updateByClient();

        setSizeFull();
        updateList();
        closeForm();
    }

    private void configureForm() {
        carRentalPreview.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("300px", 2),
                new FormLayout.ResponsiveStep("600px", 3)
        );
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, carRentalPreview);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Szukaj po rejestracji");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> update());

        filterTextCar.setPlaceholder("Szukaj po modelu");
        filterTextCar.setClearButtonVisible(true);
        filterTextCar.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextCar.addValueChangeListener(e -> updateByCarModel());

        filterTextClient.setPlaceholder("Szukaj po kliencie");
        filterTextClient.setClearButtonVisible(true);
        filterTextClient.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextClient.addValueChangeListener(e -> updateByClient());


        HorizontalLayout toolbar = new HorizontalLayout(filterText, filterTextCar, filterTextClient);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("car-rents-grid");
        grid.setSizeFull();
        grid.setColumns("rentalDate", "returnDate");
        grid.getColumnByKey("rentalDate").setHeader("Data wynajęcia");
        grid.getColumnByKey("returnDate").setHeader("Data zwrotu");

        grid.addColumn(carRent -> {
            Car car = carRent.getCar();
            return car == null ? "-" : car.getCarBrand();
        }).setHeader("Samochód");

        grid.addColumn(carRent -> {
            Client client = carRent.getClient();
            return client == null ? "-" : client.getName();
        }).setHeader("Klient");

        grid.addColumn(carRent -> {
            Client client = carRent.getClient();
            return client == null ? "-" : client.getPhoneNumber();
        }).setHeader("Telefon");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> carRentalPreview.setCarRent(grid.asSingleSelect().getValue()));

    }

    private void updateList() {
        grid.setItems(carRentService.getAllCarRents());
    }

    private void update() {
        grid.setItems(carRentService.findByRegistrationNumber(filterText.getValue()));
    }

    private void updateByCarModel() {
        grid.setItems(carRentService.findByCarModel(filterTextCar.getValue()));
    }

    private void updateByClient() {
        grid.setItems(carRentService.findByClient(filterTextClient.getValue()));
    }

    public void closeForm() {
        carRentalPreview.setCarRent(null);
        carRentalPreview.setVisible(false);
    }
}
