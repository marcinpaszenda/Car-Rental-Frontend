package com.carrentalfrontend.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {

        Image imageLogo = new Image("images/logo.png", "My logo image");
        imageLogo.addClassNames(LumoUtility.Margin.AUTO);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), imageLogo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();

        header.addClassNames("header",
                LumoUtility.Background.PRIMARY_10
                );

        addToNavbar(header);
    }

    private void createDrawer() {
        addClassNames("drawer");

        Icon mainViewIcon = new Icon(VaadinIcon.CALENDAR);
        Icon carIcon = new Icon(VaadinIcon.CAR);
        Icon carRentalsViewIcon = new Icon(VaadinIcon.FILE_SEARCH);
        Icon clientViewIcon = new Icon(VaadinIcon.USER_CARD);
        mainViewIcon.addClassNames("mainViewIcon");
        carIcon.addClassNames("carIcon");
        carRentalsViewIcon.addClassNames("carRentalsViewIcon");
        clientViewIcon.addClassNames("clientViewIcon");

        RouterLink mainView = new RouterLink("", MainView.class);
        mainView.add(mainViewIcon);
        RouterLink carView = new RouterLink("", CarView.class);
        carView.add(carIcon);
        RouterLink carRentalsView = new RouterLink("", CarRentalsView.class);
        carRentalsView.add(carRentalsViewIcon);
        RouterLink clientView = new RouterLink("", ClientView.class);
        clientView.add(clientViewIcon);

//        mainView.setHighlightCondition(HighlightConditions.sameLocation());
//        carView.setHighlightCondition(HighlightConditions.sameLocation());
//        carRentalsView.setHighlightCondition(HighlightConditions.sameLocation());
//        clientView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                mainView,
                carView,
                carRentalsView,
                clientView
        ));
    }
}
