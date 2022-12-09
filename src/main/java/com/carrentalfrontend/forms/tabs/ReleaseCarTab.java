//package com.carrentalfrontend.forms.tabs;
//
//import com.carrentalfrontend.domain.Car;
//import com.carrentalfrontend.domain.CarRent;
//import com.carrentalfrontend.domain.Client;
//import com.carrentalfrontend.domain.Driver;
//import com.carrentalfrontend.domain.enums.*;
//import com.carrentalfrontend.forms.CarRentalTabsContent;
//import com.carrentalfrontend.service.CarRentService;
//import com.carrentalfrontend.service.CarService;
//import com.carrentalfrontend.service.ClientService;
//import com.carrentalfrontend.service.DriverService;
//import com.carrentalfrontend.views.MainView;
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.tabs.Tab;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.component.timepicker.TimePicker;
//import com.vaadin.flow.data.binder.Binder;
//
//import java.time.Duration;
//
//public class ReleaseCarTab extends FormLayout {
//
//    private MainView mainView;
//    private final CarRentalTabsContent carRentalTabsContent;
//    private CarService carService = CarService.getInstance();
//    private ClientService clientService = ClientService.getInstance();
//    private DriverService driverService = DriverService.getInstance();
//    private CarRentService carRentService = CarRentService.getInstance();
//
//
//    private DatePicker rentalDate = new DatePicker("Data wynajmu");
//    private TimePicker rentalHour = new TimePicker("Godzina wynajmu");
//    private DatePicker returnDate = new DatePicker("Data zwrotu");
//    private TimePicker returnHour = new TimePicker("Godzina zwrotu");
//    private TextField rentalDayLength = new TextField("Dł. wynajmu w dniach");
//    private ComboBox<Currency> currency = new ComboBox<>("Waluta");
//    private TextField dailyRate = new TextField("Stawka dzienna");
//    private TextField additionalCosts = new TextField("Koszty dodatkowe");
//    private TextField deposit = new TextField("Depozyt");
//    private TextField totalCost = new TextField("Koszt całkowity");
//    private ComboBox<DailyMileageLimit> dailyMileageLimit = new ComboBox<>("Limit dzienny km");
//    private ComboBox<TravelAbroad> travelAbroad = new ComboBox<>("Zgoda na wyjazd za granicę");
//    private ComboBox<RegistrationCertificate> registrationCertificate = new ComboBox<>("Czy wydano dowód rej.");
//    private ComboBox<AbolitionDeductibleInDamage> abolitionDeductibleInDamage = new ComboBox<>("Zniesienie udziału własnego");
//    private TextField abolitionFee = new TextField("Opł. za zniesienie udziału wł.");
//
//
//    //samochód
//    private ComboBox<Car> car = new ComboBox<>("Samochód");
//    //    private ComboBox<Car> registrationNumber = new ComboBox<>("Numer rejestraycjny");
//    //Klient
//    private ComboBox<Client> client = new ComboBox<>("Klient");
//    //Kierowca
//    private ComboBox<Driver> driver = new ComboBox<>("Kierowca");
//    //raport wydania
//    private ComboBox<CleanCarBody> cleanCarBodyRelease = new ComboBox<>("Karoseria czysta");
//
////    private CheckboxGroup<CleanCarBody> checkboxGroup = new CheckboxGroup<>("Karoseria czysta");
//
//    private ComboBox<CleanCarInterior> cleanCarInteriorRelease = new ComboBox<>("Wnętrze czyste");
//    private TextField amountOfFuelRelease = new TextField("Stan paliwa");
//    private TextField carMileageRelease = new TextField("Przebieg");
//    private TextField remarksRelease = new TextField("Uwagi");
//
//    Button save = new Button("SAVE");
//    Button delete = new Button("DELETE");
//    Button cancel = new Button("CANCEL");
//
//    private Binder<CarRent> binder = new Binder<>(CarRent.class);
//
//    public ReleaseCarTab(CarRentalTabsContent carRentalTabsContent) {
//        this.carRentalTabsContent = carRentalTabsContent;
//
//        binder.bindInstanceFields(this);
//        car.setItems(carService.getAllCars());
//        car.setItemLabelGenerator(Car::getCarBrand);
////        registrationNumber.setItems(carService.getAllCars());
////        registrationNumber.setItemLabelGenerator(Car::getRegistrationNumber);
//        client.setItems(clientService.getAllClients());
//        client.setItemLabelGenerator(Client::getName);
//
//        //jak zrobić żeby zaczytywać kierowców wybranej firmy?
////        driver.setItems(client.getValue().getDrivers());
//
//        driver.setItems(driverService.getAllDrivers());
//        driver.setItemLabelGenerator(Driver::getDriverName);
//
//        rentalHour.setStep(Duration.ofMinutes(15));
//        returnHour.setStep(Duration.ofMinutes(1));
//
//        currency.setItems(Currency.values());
//        dailyMileageLimit.setItems(DailyMileageLimit.values());
//        travelAbroad.setItems(TravelAbroad.values());
//        registrationCertificate.setItems(RegistrationCertificate.values());
//        abolitionDeductibleInDamage.setItems(AbolitionDeductibleInDamage.values());
////        checkboxGroup.setItems(CleanCarBody.values());
////        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
//        cleanCarBodyRelease.setItems(CleanCarBody.values());
//        cleanCarInteriorRelease.setItems(CleanCarInterior.values());
//
//
//        add(
//                car,
////                registrationNumber,
//                client,
//                driver,
//                rentalDate,
//                rentalHour,
//                returnDate,
//                returnHour,
//                rentalDayLength,
//                currency,
//                dailyRate,
//                additionalCosts,
//                deposit,
//                totalCost,
//                dailyMileageLimit,
//                travelAbroad,
//                registrationCertificate,
//                abolitionDeductibleInDamage,
//                abolitionFee,
//
//                cleanCarBodyRelease,
////                checkboxGroup,
//
//                cleanCarInteriorRelease,
//                amountOfFuelRelease,
//                carMileageRelease,
//                remarksRelease
//        );
//        setSizeFull();
//    }
//
//}