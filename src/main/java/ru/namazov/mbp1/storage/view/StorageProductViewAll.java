/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.storage.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.storage.model.Storage;
import ru.namazov.mbp1.storage.model.StorageProduct;
import ru.namazov.mbp1.storage.presenter.StoragePresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "storage/storage_product", layout = MainView.class)
@PermitAll
public class StorageProductViewAll extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private final StoragePresenter storagePresenter;
    private final Grid<StorageProduct> table = new Grid<>(StorageProduct.class, false);
    private Storage storage;


    public StorageProductViewAll(StoragePresenter storagePresenter) {
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
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField storageName = new TextField();
        storageName.setValue("Склад " + storage.getName());
        storageName.setReadOnly(true);
        storageName.setSizeFull();
        horizontalLayout.add(storageName);
        add(horizontalLayout);
    }

    @Override
    public void main() {
        table.addColumn(StorageProduct::getProductName).setHeader("Название").setResizable(true);
        table.addColumn(StorageProduct::getCount).setHeader("Колличество").setResizable(true);
        table.addColumn(StorageProduct::getProductMeasurement).setHeader("Единица измерения").setResizable(true);
        table.addColumn(StorageProduct::getProductDescription).setHeader("Описание").setResizable(true);
        table.setItems(storage.getStorageProductList());
        add(table);
    }

    @Override
    public void footer() {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(i -> i.getAuthority().equals("ROLE_ADMIN")).count() == 1) {
            add(new HorizontalLayout(addButton(), editButton()));
        }
    }

    private Button editButton() {
        var editButton = new Button("Списать");
        return editButton;
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        return createButton;
    }
}
