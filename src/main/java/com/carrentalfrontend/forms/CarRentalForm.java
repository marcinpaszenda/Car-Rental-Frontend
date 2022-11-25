package com.carrentalfrontend.forms;

import com.carrentalfrontend.domain.*;
import com.carrentalfrontend.domain.enums.*;
import com.carrentalfrontend.service.*;
import com.carrentalfrontend.views.CarRentalView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

import java.time.Duration;

public class CarRentalForm extends FormLayout {

    private CarRentalView carRentalView;
    private CarRentService carRentService = CarRentService.getInstance();
    private CarService carService = CarService.getInstance();
    private ClientService clientService = ClientService.getInstance();
    private DriverService driverService = DriverService.getInstance();

    private DatePicker rentalDate = new DatePicker("Data wynajmu");
    private TimePicker rentalHour = new TimePicker("Godzina wynajmu");
    private DatePicker returnDate = new DatePicker("Data zwrotu");
    private TimePicker returnHour = new TimePicker("Godzina zwrotu");
    private TextField rentalDayLength = new TextField("Dł. wynajmu w dniach");
    private ComboBox<Currency> currency = new ComboBox<>("Waluta");
    private TextField dailyRate = new TextField("Stawka dzienna");
    private TextField additionalCosts = new TextField("Koszty dodatkowe");
    private TextField deposit = new TextField("Depozyt");
    private TextField totalCost = new TextField("Koszt całkowity");
    private ComboBox<DailyMileageLimit> dailyMileageLimit = new ComboBox<>("Limit dzienny km");
    private ComboBox<TravelAbroad> travelAbroad = new ComboBox<>("Zgoda na wyjazd za granicę");
    private ComboBox<RegistrationCertificate> registrationCertificate = new ComboBox<>("Czy wydano dowód rej.");
    private ComboBox<AbolitionDeductibleInDamage> abolitionDeductibleInDamage = new ComboBox<>("Zniesienie udziału własnego");
    private TextField abolitionFee = new TextField("Opł. za zniesienie udziału wł.");


    //samochód
    private ComboBox<Car> car = new ComboBox<>("Samochód");
    private ComboBox<Car> registrationNumber = new ComboBox<>("Numer rejestraycjny");
    //Klient
    private ComboBox<Client> client = new ComboBox<>("Klient");
    //Kierowca
    private ComboBox<Driver> driver = new ComboBox<>("Kierowca");
    //raport wydania
    private ComboBox<CleanCarBody> cleanCarBodyRelease = new ComboBox<>("Karoseria czysta");

//    private CheckboxGroup<CleanCarBody> checkboxGroup = new CheckboxGroup<>("Karoseria czysta");

    private ComboBox<CleanCarInterior> cleanCarInteriorRelease = new ComboBox<>("Wnętrze czyste");
    private TextField amountOfFuelRelease = new TextField("Stan paliwa");
    private TextField carMileageRelease = new TextField("Przebieg");
    private TextField remarksRelease = new TextField("Uwagi");
    //Raport zwrotu
    private ComboBox<CleanCarBody> cleanCarBodyReturn = new ComboBox<>("Karoseria czysta: ZWROT");
    private ComboBox<CleanCarInterior> cleanCarInteriorReturn = new ComboBox<>("Wnętrze czyste: ZWROT");
    private TextField amountOfFuelReturn = new TextField("Stan paliwa: ZWROT");
    private TextField carMileageReturn = new TextField("Przebieg: ZWROT");
    private TextField newCarDamage = new TextField("Nowe uszkodzenia pojazdu");
    private TextField remarksReturn = new TextField("Uwagi: ZWROT");
    private TextField depositRefund = new TextField("Zwrot depozytu");



    Button save = new Button("SAVE");
    Button delete = new Button("DELETE");
    Button cancel = new Button("CANCEL");

    private Binder<CarRent> binder = new Binder<>(CarRent.class);

    public CarRentalForm(CarRentalView carRentalView) throws NullPointerException {
        this.carRentalView = carRentalView;

        binder.bindInstanceFields(this);

        car.setItems(carService.getAllCars());
        car.setItemLabelGenerator(Car::getCarBrand);
        registrationNumber.setItems(carService.getAllCars());
        registrationNumber.setItemLabelGenerator(Car::getRegistrationNumber);
        client.setItems(clientService.getAllClients());
        client.setItemLabelGenerator(Client::getName);

        //jak zrobić żeby zaczytywać kierowców wybranej firmy?
//        driver.setItems(client.getValue().getDrivers());

        driver.setItems(driverService.getAllDrivers());
        driver.setItemLabelGenerator(Driver::getDriverName);

        rentalHour.setStep(Duration.ofMinutes(15));
        returnHour.setStep(Duration.ofMinutes(1));

        currency.setItems(Currency.values());
        dailyMileageLimit.setItems(DailyMileageLimit.values());
        travelAbroad.setItems(TravelAbroad.values());
        registrationCertificate.setItems(RegistrationCertificate.values());
        abolitionDeductibleInDamage.setItems(AbolitionDeductibleInDamage.values());
//        checkboxGroup.setItems(CleanCarBody.values());
//        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        cleanCarBodyRelease.setItems(CleanCarBody.values());
        cleanCarInteriorRelease.setItems(CleanCarInterior.values());
        cleanCarBodyReturn.setItems(CleanCarBody.values());
        cleanCarInteriorReturn.setItems(CleanCarInterior.values());


        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.add(createButtonLayout());

        add(
                car,
                registrationNumber,
                client,
                driver,
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

                cleanCarBodyRelease,
//                checkboxGroup,

                cleanCarInteriorRelease,
                amountOfFuelRelease,
                carMileageRelease,
                remarksRelease,

                cleanCarBodyReturn,
                cleanCarInteriorReturn,
                amountOfFuelReturn,
                carMileageReturn,
                newCarDamage,
                remarksReturn,
                depositRefund,
                buttonLayout
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
        carRentalView.closeForm();
    }

    private void delete() {
        CarRent carRent = binder.getBean();
        carRentService.deleteCarRent(carRent.getCarRentId());
        carRentalView.updateList();
    }

    private void cancel() {
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
