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

@Route(value = "client", layout = MainView.class)
@PermitAll
public class ClientView extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Client client;
    private ClientPresenter clientPresenter;
    private TextField nameField;
    private TextField telNumberField;
    private TextField contactMenField;
    private TextField emailField;

    public ClientView(ClientPresenter clientPresenter) {
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
        add(new HorizontalLayout(backButton()));
    }

    @Override
    public void main() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        nameField = new TextField();
        nameField.setValue(client.getName());
        nameField.setLabel("Название Фирмы");
        nameField.setSizeFull();
        nameField.setReadOnly(true);

        telNumberField = new TextField();
        telNumberField.setValue(client.getTelNumber());
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();
        telNumberField.setReadOnly(true);

        contactMenField = new TextField();
        contactMenField.setValue(client.getContactMen());
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();
        contactMenField.setReadOnly(true);

        emailField = new TextField();
        emailField.setValue(client.getEmail());
        emailField.setLabel("Почта");
        emailField.setSizeFull();
        emailField.setReadOnly(true);

        add(nameField);
        add(telNumberField);
        add(contactMenField);
        add(emailField);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton()));
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ClientViewAll.class);
        });
        return backButton;
    }
}
