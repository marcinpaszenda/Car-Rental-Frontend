package com.carrentalfrontend.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H4 logo = new H4("CAR RENTAL CRM");
        logo.addClassNames("text-l", "m-m");

//        logo.addClassNames(LumoUtility.FontSize.XXSMALL, LumoUtility.Background.PRIMARY_10);


        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
//        header.addClassNames("py-0", "px-m");

        header.addClassNames(LumoUtility.Background.SUCCESS_10);
//        header.add(myapp);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink carRentalView = new RouterLink("Car rentals", CarRentalView.class);
        carRentalView.setHighlightCondition(HighlightConditions.sameLocation());

        carRentalView.addClassNames(LumoUtility.Background.CONTRAST_5);

        addToDrawer(new VerticalLayout(
                carRentalView
        ));

    }
}
