package com.carrentalfrontend.views;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.service.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "car-view", layout = MainLayout.class)
@PageTitle(("CarView"))
public class CarView extends VerticalLayout {

    private Grid<Car> grid = new Grid<>(Car.class);
    private CarService carService = CarService.getInstance();

    public CarView() {
        addClassName("car-view");
        configureGrid();
        add(getContent());
        setSizeFull();
        updateCarList();
    }

    private void configureGrid() {
        grid.addClassName("car-grid");
        grid.setSizeFull();
        grid.setColumns("car", "registrationNumber", "carMileage", "carDamage");
        grid.getColumnByKey("car").setHeader("Samochód");
        grid.getColumnByKey("registrationNumber").setHeader("Nr rejestracyjny");
        grid.getColumnByKey("carMileage").setHeader("Przebieg");
        grid.addColumn(Car::getCarStatus).setHeader("Status");
        grid.getColumnByKey("carDamage").setHeader("Uszkodzenia");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("contentCarView");
        content.setSizeFull();
        return content;
    }

    public void updateCarList() {
        grid.setItems(carService.getAllCars());
    }
}
