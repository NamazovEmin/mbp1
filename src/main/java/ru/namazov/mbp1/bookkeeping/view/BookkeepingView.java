/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "/admin/bookkeeping", layout = MainView.class)
@RolesAllowed({"ADMIN", "BOOKKEEPER"})
public class BookkeepingView extends VerticalLayout implements ViewConstructor {

    public BookkeepingView() {
        header();
        main();
        footer();
    }

    private void createTable() {
        storagesLine();
    }

    private void storagesLine() {
        add(storageButton());
    }

    private Button storageButton() {
        var storageButton =new Button("Приход на склад");
        storageButton.addSingleClickListener(click -> UI.getCurrent().navigate(InvoiceViewAll.class));
        return storageButton;
    }

    @Override
    public void header() {

    }

    @Override
    public void main() {
        createTable();
    }

    @Override
    public void footer() {

    }
}
