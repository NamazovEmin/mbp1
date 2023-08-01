/*
 * Copyright (c) . Namazov Emin. All rights reserved.
 */

package ru.namazov.mbp1.view;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.presenter.LoginPresenter;

@Route(value = "")
public class LoginView extends VerticalLayout {

    private final LoginPresenter loginPresenter;
    private LoginForm loginForm;

    public LoginView(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        setAlignItems(Alignment.CENTER);
        initComponent();
        addToView();
    }

    private void initComponent() {
        initLoginForm();
    }

    private void addToView() {
        add(loginForm);
    }

    private void initLoginForm() {
        loginForm = new LoginForm();
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.addLoginListener(auth -> {
          loginPresenter.auth(auth.getUsername(), auth.getPassword());
          loginForm.getUI().orElseThrow().navigate(MainView.class);
        });
    }
}