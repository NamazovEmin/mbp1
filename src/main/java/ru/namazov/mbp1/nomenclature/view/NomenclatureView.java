/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.nomenclature.view.product.ProductView;
import ru.namazov.mbp1.view.MainView;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "/admin/nomenclature", layout = MainView.class)
@RolesAllowed("ADMIN")
public class NomenclatureView extends VerticalLayout {

    public NomenclatureView() {
        createTable();
    }

    private void createTable() {
        productsLine();
        storagesLine();
    }

    private void productsLine() {
        add(productButton());
    }

    private Button productButton() {
        var productButton = new Button("Продукты");
        productButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(ProductView.class);
        });
        return productButton;
    }

    private void storagesLine() {
        add(storageButton());
    }

    private Button storageButton() {
        var storageButton =new Button("Склады");
        storageButton.addSingleClickListener(click -> {

        });
        return storageButton;
    }
}
