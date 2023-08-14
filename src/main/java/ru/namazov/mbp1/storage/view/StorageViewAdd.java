/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.client.view.ClientViewAll;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.presenter.StoragePresenter;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "storage/add", layout = MainView.class)
@RolesAllowed("ADMIN")
public class StorageViewAdd extends VerticalLayout implements ViewConstructor {

    private StoragePresenter storagePresenter;
    private TextField nameField;

    public StorageViewAdd(StoragePresenter storagePresenter) {
        this.storagePresenter = storagePresenter;
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
        nameField.setLabel("Название Склада");
        nameField.setSizeFull();

        add(nameField);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            Storage storage = new Storage();
            storage.setName(nameField.getValue());
            storagePresenter.save(storage);
            UI.getCurrent().navigate(StorageViewAll.class);
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
