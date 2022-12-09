package com.carrentalfrontend.views;

import com.carrentalfrontend.domain.Client;
import com.carrentalfrontend.service.ClientService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "client-view", layout = MainLayout.class)
@PageTitle(("ClientView"))
public class ClientView extends VerticalLayout {

    private final Grid<Client> grid = new Grid<>(Client.class);
    private final TextField filterText = new TextField();
    private final ClientService clientService = ClientService.getInstance();

    public ClientView() {
        addClassName("client-view");
        configureGrid();
        add(getToolbar(), getContent());
        update();
        setSizeFull();
        updateClientList();
    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();

        grid.setColumns("name", "street", "streetNumber", "postalCode", "city", "country",
                "phoneNumber", "email", "identityCardNumber", "drivingLicenseNumber", "identificationNumber");

        grid.getColumnByKey("name").setHeader("Nazwa");
        grid.getColumnByKey("street").setHeader("Ulica");
        grid.getColumnByKey("streetNumber").setHeader("Nr budynku");
        grid.getColumnByKey("postalCode").setHeader("Kod pocztowy");
        grid.getColumnByKey("city").setHeader("Miasto");
        grid.getColumnByKey("country").setHeader("Kraj");
        grid.getColumnByKey("phoneNumber").setHeader("Nr telefonu");
        grid.getColumnByKey("email").setHeader("e-mail");
        grid.getColumnByKey("identityCardNumber").setHeader("Nr dowodu os.");
        grid.getColumnByKey("drivingLicenseNumber").setHeader("Nr prawo jazdy");
        grid.getColumnByKey("identificationNumber").setHeader("Numer identyfikacyjny");
        grid.addColumn(Client::getTypeOfIdentificationNumber).setHeader("Typ numeru identyfikacyjnego");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Szukaj po nazwie");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> update());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar-client");
        return toolbar;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("contentClientView");
        content.setSizeFull();
        return content;
    }

    public void updateClientList() {
        grid.setItems(clientService.getAllClients());
    }

    private void update() {
        grid.setItems(clientService.findByName(filterText.getValue()));
    }
}
