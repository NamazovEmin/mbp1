/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.model.Product;
import ru.namazov.mbp1.presenter.StoragePresenter;

@Route(value = "storage", layout = BaseView.class)
public class StorageView extends VerticalLayout{

    private final StoragePresenter storagePresenter;
    private final Grid<Product> table = new Grid<>(Product.class, false);


    public StorageView(StoragePresenter storagePresenter) {
        this.storagePresenter = storagePresenter;
        createTable();
        createFooter();
    }

    private void createFooter() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(StorageEditView.class));
        add(createButton);
    }

    private void createTable() {
        table.addColumn(Product::getName).setHeader("Название").setResizable(true);
        table.addColumn(Product::getCount).setHeader("Колличество").setResizable(true);
        table.addColumn(Product::getMeasurement).setHeader("Единица измерения").setResizable(true);
        table.addColumn(Product::getDescription).setHeader("Описание").setResizable(true);
        table.setItems(storagePresenter.findAll());
        table.addItemClickListener(click -> {
            Product product = table.asSingleSelect().getValue();
            if (product != null) {
                UI.getCurrent().navigate(StorageEditView.class, product.getId());
            }
        });
        add(table);
    }
}
