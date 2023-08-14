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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "client/edit", layout = MainView.class)
@PermitAll
public class ClientViewEdit extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Client client;
    private ClientPresenter clientPresenter;
    private TextField nameField;
    private TextField telNumberField;
    private TextField contactMenField;
    private TextField emailField;

    public ClientViewEdit(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            client = clientPresenter.findById(id);
            header();
            main();
            footer();
        }
    }

    @Override
    public void header() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    @Override
    public void main() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        nameField = new TextField();
        nameField.setValue(client.getName());
        nameField.setLabel("Название Фирмы");
        nameField.setSizeFull();

        telNumberField = new TextField();
        telNumberField.setValue(client.getTelNumber());
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();

        contactMenField = new TextField();
        contactMenField.setValue(client.getContactMen());
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();

        emailField = new TextField();
        emailField.setValue(client.getEmail());
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
