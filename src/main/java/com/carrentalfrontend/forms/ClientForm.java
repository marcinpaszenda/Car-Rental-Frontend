package com.carrentalfrontend.forms;

import com.carrentalfrontend.domain.Client;
import com.carrentalfrontend.domain.enums.TypeOfIdentificationNumber;
import com.carrentalfrontend.service.ClientService;
import com.carrentalfrontend.views.CarRentalView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ClientForm extends FormLayout {

    private CarRentalView carRentalView;
//    private ClientForm clientForm;
    private ClientService clientService = ClientService.getInstance();

    //klient
    private TextField name = new TextField("Nazwa klienta");
    private TextField street = new TextField("Ulica");
    private TextField streetNumber = new TextField("Nr budynku");
    private TextField postalCode = new TextField("Kod pocztowy");
    private TextField city = new TextField("Miasto");
    private TextField country = new TextField("Kraj");
    private TextField placeOfBirth = new TextField("Miejsce urodzenia");
    private ComboBox<TypeOfIdentificationNumber> typeOfIdentificationNumber = new ComboBox<>("Rodzaj nr identyfikacyjnego");
    private TextField identificationNumber = new TextField("PESEL/NIP/REGON");
    private TextField identityCardNumber = new TextField("Nr dowodu os.");
    private TextField drivingLicenseNumber = new TextField("Nr prawo jazdy");
    private TextField phoneNumber = new TextField("Nr telefonu");
    private TextField email = new TextField("Email");

    Button save = new Button("SAVE");
    Button delete = new Button("DELETE");
    Button cancel = new Button("CANCEL");

    private Binder<Client> binderClient = new Binder<>(Client.class);

    public ClientForm(CarRentalView carRentalView) {
        this.carRentalView = carRentalView;
        binderClient.bindInstanceFields(this);

        typeOfIdentificationNumber.setItems(TypeOfIdentificationNumber.values());

        add(
                name,
                street,
                streetNumber,
                postalCode,
                city,
                country,
                placeOfBirth,
                typeOfIdentificationNumber,
                identificationNumber,
                identityCardNumber,
                drivingLicenseNumber,
                phoneNumber,
                email,
                createButtonLayout()
            );
    }

    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> saveClient());
        delete.addClickListener(event -> deleteClient());
        cancel.addClickListener(event -> cancelClientForm());

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    private void saveClient() {
        Client client = binderClient.getBean();
        clientService.saveNewClient(client);
        carRentalView.updateList();
        carRentalView.closeClientForm();
    }

    private void deleteClient() {
        Client client = binderClient.getBean();
        clientService.deleteClient(client.getClientId());
        carRentalView.updateList();
        carRentalView.closeClientForm();

    }

    private void cancelClientForm() {
        carRentalView.closeClientForm();
    }

    public void setClient(Client client) {
        binderClient.setBean(client);

        if (client == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }



}
