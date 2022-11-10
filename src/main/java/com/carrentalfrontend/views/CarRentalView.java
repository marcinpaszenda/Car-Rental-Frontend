package com.carrentalfrontend.views;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.domain.CarRent;
import com.carrentalfrontend.domain.Driver;
import com.carrentalfrontend.forms.CarRentalForm;
import com.carrentalfrontend.service.CarRentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;


@PageTitle("Car rents list | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
public class CarRentalView extends VerticalLayout {

    private Grid<CarRent> grid = new Grid<>(CarRent.class);

    private TextField filterText = new TextField();

    private CarRentService carRentService = CarRentService.getInstance();

    private CarRentalForm carRentalForm = new CarRentalForm(this);

    public CarRentalView() {
        addClassName("car-rents-view");

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        update();
        setSizeFull();
        updateList();
        closeForm();

    }

    public void closeForm() {
        carRentalForm.setCarRent(null);
        carRentalForm.setVisible(false);
        removeClassName("editing");
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, carRentalForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        carRentalForm = new CarRentalForm(this);
        carRentalForm.setWidth("100%");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Szukaj po rejestracji");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> update());

        Button addNewCarRental = new Button("Nowy wynajem");
        addNewCarRental.addClickListener(event -> addContactButton());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addNewCarRental);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContactButton() {
        grid.asSingleSelect().clear();
        carRentalForm.setCarRent(new CarRent());
    }

    private void configureGrid() {
        grid.addClassName("car-rents-grid");
        grid.setSizeFull();

        grid.setColumns("rentalDate", "rentalHour");


        grid.addColumn(carRent -> {
            Car car = carRent.getCar();
            return car == null ? "-" : car.getCarBrand();
        }).setHeader("Car");

        grid.addColumn(carRent -> {
            Car car = carRent.getCar();
            return car == null ? "-" : car.getRegistrationNumber();
        }).setHeader("Registration Number");

        grid.addColumn(carRent -> {
            Driver driver = carRent.getDriver();
            return driver == null ? "-" : driver.getDriverName();
        }).setHeader("Driver");

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
