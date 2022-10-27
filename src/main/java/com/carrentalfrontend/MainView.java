package com.carrentalfrontend;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private CarService carService = CarService.getInstance();
    private Grid<Car> grid = new Grid<>(Car.class);
    private TextField filter = new TextField();
    private CarForm form = new CarForm(this);
    private Button addNewCar = new Button("Add new car");



    public MainView() {
        filter.setPlaceholder("Filter by car brand");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("carBrand", "registrationNumber", "carMileage", "vinNumber", "carClass");

        addNewCar.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCar(new Car());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewCar);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setCar(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setCar(grid.asSingleSelect().getValue()));
    }

    private void update() {
        grid.setItems(carService.findByCarBrand(filter.getValue()));
    }

    public void refresh() {
        grid.setItems(carService.getCars());
    }
}
