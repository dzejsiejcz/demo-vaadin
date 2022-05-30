package com.example.demovaadin.ui.views.dashboard;

import com.example.demovaadin.backend.service.CompanyService;
import com.example.demovaadin.backend.service.ContactsService;
import com.example.demovaadin.ui.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    private final ContactsService contactsService;
    private final CompanyService companyService;

    public DashboardView(ContactsService contactsService,
                         CompanyService companyService) {
        this.contactsService = contactsService;
        this.companyService = companyService;

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(
                getContactStats()

        );
    }


    private Span getContactStats() {
        Span stats = new Span(contactsService.count() + " contacts");
        stats.addClassName("contact-stats");
        return stats;
    }
}
