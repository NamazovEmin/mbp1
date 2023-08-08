/*
 * Copyright (c) . Namazov Emin. All rights reserved.
 */

package ru.namazov.mbp1.login.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import ru.namazov.mbp1.login.presenter.LoginPresenter;

@Route(value = "/login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public LoginView(LoginPresenter loginPresenter) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        var login = new LoginForm();
        login.setAction("login");

        add(new H1("Todo app"), login);
    }
}