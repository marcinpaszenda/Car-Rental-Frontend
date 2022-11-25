package com.carrentalfrontend.views;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.domain.CarRent;
import com.carrentalfrontend.domain.Client;
import com.carrentalfrontend.domain.Driver;
import com.carrentalfrontend.forms.CarRentalForm;
import com.carrentalfrontend.forms.ClientForm;
import com.carrentalfrontend.forms.DriverForm;
import com.carrentalfrontend.service.CarRentService;
import com.carrentalfrontend.service.ClientService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Car rents list | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
public class CarRentalView extends VerticalLayout {


    private Grid<CarRent> grid = new Grid<>(CarRent.class);

    private TextField filterText = new TextField();

    private CarRentService carRentService = CarRentService.getInstance();
    private ClientService clientService = ClientService.getInstance();

    private ClientForm clientForm = new ClientForm(this);
    private DriverForm driverForm = new DriverForm(this);
    private CarRentalForm carRentalForm = new CarRentalForm(this);

    public CarRentalView() {
        addClassName("car-rents-view");

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        update();
        setSizeFull();
        updateList();
        closeClientForm();
        closeDriverForm();
        closeForm();
    }

    public void closeForm() {
        carRentalForm.setCarRent(null);
        carRentalForm.setVisible(false);
        removeClassName("editing");
    }

    public void closeClientForm() {
        clientForm.setClient(null);
        clientForm.setVisible(false);
    }

    public void closeDriverForm() {
        driverForm.setDriver(null);
        driverForm.setVisible(false);
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, clientForm, driverForm, carRentalForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        carRentalForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("300px", 2),
                new FormLayout.ResponsiveStep("500px", 3)

        );
        clientForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("300px", 2),
                new FormLayout.ResponsiveStep("500px", 3)

        );

        driverForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("300px", 2)
        );
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Szukaj po rejestracji");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> update());

        Button addNewClient = new Button("Dodaj nowego klienta");
        addNewClient.addClickListener(event -> addClientButton());

        Button addNewDriver = new Button("Dodaj nowego kierowcę");
        addNewDriver.addClickListener(event -> addDriverButton());

        Button addNewCarRental = new Button("Nowy wynajem");
        addNewCarRental.addClickListener(event -> addContactButton());


        HorizontalLayout toolbar = new HorizontalLayout(filterText, addNewClient, addNewDriver, addNewCarRental);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addClientButton() {
        grid.asSingleSelect().clear();
        clientForm.setClient(new Client());
    }

    private void addDriverButton() {
        grid.asSingleSelect().clear();
        driverForm.setDriver(new Driver());
    }

    private void addContactButton() {
        grid.asSingleSelect().clear();
        carRentalForm.setCarRent(new CarRent());
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

//        grid.addColumn(carRent -> {
//            Car car = carRent.getCar();
//            return car == null ? "-" : car.getRegistrationNumber();
//        }).setHeader("Nr rej.");

        grid.addColumn(carRent -> {
            Client client = carRent.getClient();
            return client == null ? "-" : client.getName();
        }).setHeader("Klient");

        grid.addColumn(carRent -> {
            Client client = carRent.getClient();
            return client == null ? "-" : client.getPhoneNumber();
        }).setHeader("Telefon");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        //wybieramy pojedynczy rekord do formularza
        grid.asSingleSelect().addValueChangeListener(event -> carRentalForm.setCarRent(grid.asSingleSelect().getValue()));

    }

    public void updateList() {
        grid.setItems(carRentService.getAllCarRents());
    }

    private void update() {
        grid.setItems(carRentService.findByRegistrationNumber(filterText.getValue()));
    }

}
