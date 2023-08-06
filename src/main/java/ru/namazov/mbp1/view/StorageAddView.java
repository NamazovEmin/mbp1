/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.storege.model.Storage;
import ru.namazov.mbp1.storege.presenter.StoragesPresenter;
import ru.namazov.mbp1.storege.view.StoragesView;

@Route(value = "storages/add", layout = MainView.class)
public class StorageAddView extends VerticalLayout {

    private final StoragesPresenter storagesPresenter;

    public StorageAddView(StoragesPresenter storagesPresenter) {
        this.storagesPresenter = storagesPresenter;
        createTableCreate();
    }

    private void createTableCreate() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        add(horizontalLayout);

        var nameField = new TextField();
        nameField.setLabel("Название");
        nameField.setSizeFull();

        add(nameField);

        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(StoragesView.class);
        });

        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            Storage storage = new Storage();
            storage.setName(nameField.getValue());
            storagesPresenter.save(storage);
            nameField.setReadOnly(true);
        });

        horizontalLayout.add(backButton, saveButton);
    }
}
