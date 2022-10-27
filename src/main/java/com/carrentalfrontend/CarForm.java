package com.carrentalfrontend;

import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.service.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CarForm extends FormLayout {

    private MainView mainView;
    private TextField carBrand = new TextField("Model");
    private TextField registrationNumber = new TextField("Numer rejestracyjny");
    private TextField carMileage = new TextField("Car mileage");
    private TextField vinNumber = new TextField("VIN number");
    private ComboBox<CarClass> carClass = new ComboBox<>("Car class");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Car> binder = new Binder<Car>(Car.class);
    private CarService service = CarService.getInstance();


    public CarForm(MainView mainView) {
        carClass.setItems(CarClass.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(carBrand, registrationNumber,carMileage, vinNumber, carClass, buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    private void save() {
        Car car = binder.getBean();
        service.save(car);
        mainView.refresh();
        setCar(null);
    }

    private void delete() {
        Car car = binder.getBean();
        service.delete(car);
        mainView.refresh();
        setCar(null);
    }

    public void setCar(Car car) {
        binder.setBean(car);

        if (car == null) {
            setVisible(false);
        } else {
            setVisible(true);
            carBrand.focus();
        }
    }
}
