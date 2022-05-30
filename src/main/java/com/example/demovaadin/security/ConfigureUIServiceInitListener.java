package com.example.demovaadin.security;

import com.example.demovaadin.ui.views.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter);
        });
    }

    private void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!LoginView.class.equals(beforeEnterEvent.getNavigationTarget())
        && !SecurityUtils.isUserLoggedIn()) {
            beforeEnterEvent.rerouteTo(LoginView.class);
        }
    }
}
