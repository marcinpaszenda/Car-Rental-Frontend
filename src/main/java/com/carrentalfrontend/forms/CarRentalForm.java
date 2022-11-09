package com.carrentalfrontend.forms;

import com.carrentalfrontend.CarRentalView;
import com.carrentalfrontend.domain.Car;
import com.carrentalfrontend.domain.CarRent;
import com.carrentalfrontend.domain.Currency;
import com.carrentalfrontend.service.CarRentService;
import com.carrentalfrontend.service.CarService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.time.LocalDate;
import java.time.LocalTime;

public class CarRentalForm extends FormLayout {

    private CarRentalView carRentalView;
    private CarRentService carRentService = CarRentService.getInstance();
    private CarService carService = CarService.getInstance();
    private CarRentalForm carRentalForm;

    private DatePicker rentalDate = new DatePicker("Rental date", LocalDate.now());
    private TimePicker rentalHour = new TimePicker("Rental hour");
    private DatePicker returnDate = new DatePicker("Return date", LocalDate.now().plusDays(5));
    private TimePicker returnHour = new TimePicker("Return hour");
    private TextField rentalDayLength = new TextField("Rental Day Length");
    private ComboBox<Currency> currency = new ComboBox<>("Currency");
    private TextField dailyRate = new TextField("Daily rate");
    private TextField additionalCosts = new TextField("additional Costs");
    private TextField deposit = new TextField("deposit");
    private TextField totalCost = new TextField("total Cost");
    private RadioButtonGroup<Boolean> dailyMileageLimit = new RadioButtonGroup<>("daily Mileage Limit");
    private TextField travelAbroad = new TextField("travel Abroad");
    private TextField registrationCertificate = new TextField("registration Certificate");
    private TextField abolitionDeductibleInDamage = new TextField("abolition Deductible In Damage");
    private TextField abolitionFee = new TextField("abolition Fee");
    private ComboBox<Car> car = new ComboBox<>("Car");
    private ComboBox<Car> registrationNumber = new ComboBox<>("Registration number");


    Button save = new Button("SAVE");
    Button delete = new Button("DELETE");
    Button cancel = new Button("CANCEL");

    private Binder<CarRent> binder = new Binder<>(CarRent.class);

    public CarRentalForm(CarRentalView carRentalView) {
        this.carRentalView = carRentalView;

        binder.bindInstanceFields(this);

        car.setItems(carService.getAllCars());
        car.setItemLabelGenerator(Car::getCarBrand);
        registrationNumber.setItems(carService.getAllCars());
        registrationNumber.setItemLabelGenerator(Car::getRegistrationNumber);


        currency.setItems(Currency.values());
//        rentalHour.setValue(LocalTime.now());
//        returnHour.setValue(LocalTime.now().plusHours(5));
        dailyMileageLimit.setItems(true, false);
        add(
                car,
                registrationNumber,
                rentalDate,
                rentalHour,
                returnDate,
                returnHour,
                rentalDayLength,
                currency,
                dailyRate,
                additionalCosts,
                deposit,
                totalCost,
                dailyMileageLimit,
                travelAbroad,
                registrationCertificate,
                abolitionDeductibleInDamage,
                abolitionFee,
                createButtonLayout()
        );

    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        cancel.addClickListener(event -> cancel());


        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void save() {
        CarRent carRent = binder.getBean();
        carRentService.saveNewCarRent(carRent);
        carRentalView.updateList();
    }

    public void delete() {
        CarRent carRent = binder.getBean();
        carRentService.deleteCarRent(carRent.getCarRentId());
        carRentalView.updateList();
    }

    public void cancel() {
        carRentalView.closeForm();
    }


    public void setCarRent(CarRent carRent) {
        binder.setBean(carRent);

        if (carRent == null) {
            setVisible(false);
        } else {
            setVisible(true);
//            rentalDate.focus();
        }
    }

}
