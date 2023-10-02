/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.presenter.InvoicePresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "/admin/bookkeeping/invoice", layout = MainView.class)
@PermitAll
public class InvoiceViewAll extends VerticalLayout implements ViewConstructor {

    private final InvoicePresenter invoicePresenter;
    private final Grid<Invoice> table = new Grid<>(Invoice.class, false);

    public InvoiceViewAll(InvoicePresenter invoicePresenter) {
        this.invoicePresenter = invoicePresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        TextField nameField = new TextField();
        nameField.setValue("Список документов прихода");
        nameField.setSizeFull();
        nameField.setReadOnly(true);
        add(nameField);
    }

    @Override
    public void main() {
        table.addColumn(Invoice::getNumber).setHeader("Номер накладной").setResizable(true);
        table.addColumn(Invoice::getDate).setHeader("Дата").setResizable(true);
        table.addColumn(Invoice::getInvoiceProductList).setHeader("Продукты").setResizable(true);
        table.setItems(invoicePresenter.findAll());
        add(table);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(addButton(), editButton(), readButton()));
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(InvoiceViewAdd.class));
        return createButton;
    }

    private Button editButton() {
        var editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            Invoice invoice = table.asSingleSelect().getValue();
            if (invoice != null) {
                UI.getCurrent().navigate(InvoiceViewEdit.class, invoice.getId());
            }
        });
        return editButton;
    }

    private Button readButton() {
        var readButton = new Button("Просмотреть");
        readButton.addSingleClickListener(click -> {
            Invoice invoice = table.asSingleSelect().getValue();
            if (invoice != null) {
                UI.getCurrent().navigate(InvoiceView.class, invoice.getId());
            }
        });
        return readButton;
    }
}
