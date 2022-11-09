package com.carrentalfrontend;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CarRentalFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalFrontendApplication.class, args);
    }

}
