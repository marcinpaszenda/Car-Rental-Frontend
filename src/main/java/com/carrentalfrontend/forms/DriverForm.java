package com.carrentalfrontend.forms;

import com.carrentalfrontend.domain.Client;
import com.carrentalfrontend.domain.Driver;
import com.carrentalfrontend.service.ClientService;
import com.carrentalfrontend.service.DriverService;
import com.carrentalfrontend.views.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class DriverForm extends FormLayout {

    private MainView mainView;
    private DriverService driverService = DriverService.getInstance();
    private ClientService clientService = ClientService.getInstance();

    private TextField driverName = new TextField("Imię i nazwisko");
    private TextField identityCardNumber = new TextField("Nr dowodu os.");
    private TextField drivingLicenseNumber = new TextField("Nr prawo jazdy");
    private TextField phoneNumber = new TextField("Nr telefonu");
    private ComboBox<Client> client = new ComboBox<>("Klient");

    Button save = new Button("SAVE");
    Button delete = new Button("DELETE");
    Button cancel = new Button("CANCEL");

    private Binder<Driver> binderDriver = new Binder<>(Driver.class);

    public DriverForm(MainView mainView) {
        this.mainView = mainView;
        binderDriver.bindInstanceFields(this);

        client.setItems(clientService.getAllClients());
        client.setItemLabelGenerator(Client::getName);



        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.add(createButtonLayout());

        add(
                driverName,
                identityCardNumber,
                drivingLicenseNumber,
                phoneNumber,
                client,
                buttonLayout
        );
    }

        private Component createButtonLayout () {
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            save.addClickListener(event -> saveDriver());
            delete.addClickListener(event -> deleteDriver());
            cancel.addClickListener(event -> cancelDriver());

            save.addClickShortcut(Key.ENTER);
            cancel.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, cancel);
        }

        private void saveDriver() {
            Driver driver = binderDriver.getBean();
            driverService.saveNewDriver(driver);
            UI.getCurrent().getPage().reload();
            mainView.updateList();
            mainView.closeDriverForm();
        }

        private void deleteDriver() {
            Driver driver = binderDriver.getBean();
            driverService.deleteDriver(driver.getDriverId());
            mainView.updateList();
            mainView.closeDriverForm();
        }

        private void cancelDriver() {
            mainView.closeDriverForm();
        }

        public void setDriver (Driver driver){
            binderDriver.setBean(driver);

            if (driver == null) {
                setVisible(false);
            } else {
                setVisible(true);
            }
        }

    }
