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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.presenter.StoragePresenter;
import ru.namazov.mbp1.view.MainView;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "storage/edit", layout = MainView.class)
@RolesAllowed("ADMIN")
public class StorageViewEdit extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Storage storage;
    private StoragePresenter storagePresenter;
    private TextField nameField;

    public StorageViewEdit(StoragePresenter storagePresenter) {
        this.storagePresenter = storagePresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            storage = storagePresenter.findById(id);
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
        nameField.setValue(storage.getName());
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
            storage.setName(nameField.getValue());
            storagePresenter.save(storage);
            UI.getCurrent().navigate(StorageViewAll.class);
        });

        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(StorageViewAll.class);
        });
        return backButton;
    }
}
