package com.example.demovaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@PWA(
        name = "Vaadin CRM",
        shortName = "CRM",
        offlineResources = {
                "./images/offline.png"
        }
)
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class DemoVaadinApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(DemoVaadinApplication.class, args);
    }

}
