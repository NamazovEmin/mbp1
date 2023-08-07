/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.client.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.view.MainView;

import jakarta.annotation.security.PermitAll;

@Route(value = "client/add", layout = MainView.class)
@PermitAll
public class ClientViewAdd extends VerticalLayout implements ViewConstructor {

    private ClientPresenter clientPresenter;
    private TextField nameField;
    private TextField telNumberField;
    private TextField contactMenField;
    private TextField emailField;

    public ClientViewAdd(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    @Override
    public void main() {
        nameField = new TextField();
        nameField.setLabel("Название Фирмы");
        nameField.setSizeFull();

        telNumberField = new TextField();
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();

        contactMenField = new TextField();
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();

        emailField = new TextField();
        emailField.setLabel("Почта");
        emailField.setSizeFull();

        add(nameField);
        add(telNumberField);
        add(contactMenField);
        add(emailField);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            Client client = new Client();
            client.setName(nameField.getValue());
            client.setTelNumber(telNumberField.getValue());
            client.setContactMen(contactMenField.getValue());
            client.setEmail(emailField.getValue());
            clientPresenter.save(client);
            UI.getCurrent().navigate(ClientViewAll.class);
        });
        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ClientViewAll.class);
        });
        return backButton;
    }
}
