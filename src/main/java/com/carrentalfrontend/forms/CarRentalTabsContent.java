//package com.carrentalfrontend.forms;
//
//import com.carrentalfrontend.forms.tabs.ReleaseCarTab;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.tabs.Tab;
//import com.vaadin.flow.component.tabs.Tabs;
//import com.vaadin.flow.component.tabs.TabsVariant;
//import com.vaadin.flow.theme.lumo.LumoUtility;
//
//public class CarRentalTabsContent extends Div {
//
//    private final Tab releaseCar;
//    private final Tab returnCar;
//    private final VerticalLayout content;
//    private final CarRentalForm carRentalForm;
//
//    private ReleaseCarTab releaseCarTab = new ReleaseCarTab(this);
//
//
//
//    public CarRentalTabsContent(CarRentalForm carRentalForm) {
//        this.carRentalForm = carRentalForm;
//
//        releaseCar = new Tab("Wydanie pojazdu");
//        returnCar = new Tab("Zwrot pojazdu");
//
//        Tabs tabs = new Tabs(releaseCar, returnCar);
//        tabs.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
//        tabs.addSelectedChangeListener(event -> setContent(event.getSelectedTab()));
//
//        content = new VerticalLayout();
//        content.setSpacing(false);
//        setContent(tabs.getSelectedTab());
//
//        configureForm();
//
//        add(tabs, content);
//    }
//
//    private void setContent(Tab tab) {
//        content.removeAll();
//
//        if (tab.equals(returnCar)) {
//            content.add(new Paragraph("This is return tab"));
//        } else {
//            content.add(new Tab(releaseCarTab));
//        }
//    }
//
//    private void configureForm() {
//
//        releaseCarTab.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("0", 1),
//                new FormLayout.ResponsiveStep("200px", 2),
//                new FormLayout.ResponsiveStep("400px", 3)
//        );
//
//    }
//}
