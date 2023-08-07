/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.client.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.client.model.Client;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.view.MainView;

import jakarta.annotation.security.PermitAll;

@Route(value = "client", layout = MainView.class)
@PermitAll
public class ClientViewAll extends VerticalLayout implements ViewConstructor {

    private final ClientPresenter clientPresenter;
    private final Grid<Client> table = new Grid<>(Client.class, false);

    public ClientViewAll(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        TextField nameField = new TextField();
        nameField.setValue("Список Клиентов");
        nameField.setSizeFull();
        nameField.setReadOnly(true);
        add(nameField);
    }

    @Override
    public void main() {
        table.addColumn(Client::getName).setHeader("Название фирмы").setResizable(true);
        table.addColumn(Client::getContactMen).setHeader("Контактное лицо").setResizable(true);
        table.addColumn(Client::getTelNumber).setHeader("Телефон").setResizable(true);
        table.addColumn(Client::getEmail).setHeader("Почта").setResizable(true);
        table.setItems(clientPresenter.findAll());
        add(table);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(addButton(), editButton(), readButton()));
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(ClientViewAdd.class));
        return createButton;
    }

    private Button editButton() {
        var editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            Client client = table.asSingleSelect().getValue();
            if (client != null) {
                UI.getCurrent().navigate(ClientViewEdit.class, client.getId());
            }
        });
        return editButton;
    }

    private Button readButton() {
        var readButton = new Button("Просмотреть");
        readButton.addSingleClickListener(click -> {
            Client client = table.asSingleSelect().getValue();
            if (client != null) {
                UI.getCurrent().navigate(ClientView.class, client.getId());
            }
        });
        return readButton;
    }
}
