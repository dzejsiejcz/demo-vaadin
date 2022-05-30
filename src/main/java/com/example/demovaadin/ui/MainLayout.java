package com.example.demovaadin.ui;

import com.example.demovaadin.security.SecurityUtils;
import com.example.demovaadin.ui.views.dashboard.DashboardView;
import com.example.demovaadin.ui.views.list.ListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;


@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    private final SecurityUtils securityUtils;

    public MainLayout(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", DashboardView.class)));
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");

//        Anchor logout = new Anchor("/logout", "Log out");
        Button logout = new Button("Log out", e -> securityUtils.logout());


        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.expand(logo);
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }
}
