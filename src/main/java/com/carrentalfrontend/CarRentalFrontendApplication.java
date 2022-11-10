package com.carrentalfrontend;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@Theme("myapp")
public class CarRentalFrontendApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalFrontendApplication.class, args);
    }

}
