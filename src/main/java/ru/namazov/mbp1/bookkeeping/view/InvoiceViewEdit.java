/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.presenter.InvoicePresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "/admin/bookkeeping/invoice/edit", layout = MainView.class)
@PermitAll
public class InvoiceViewEdit extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Invoice invoice;
    private InvoicePresenter invoicePresenter;
    private DatePicker dateField;
    private TextField numberField;
    private TextField isInStockField;

    public InvoiceViewEdit(InvoicePresenter invoicePresenter) {
        this.invoicePresenter = invoicePresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            invoice = invoicePresenter.findById(id);
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

        dateField = new DatePicker("Дата");

        dateField.setSizeFull();
        dateField.setValue(LocalDate.ofInstant(invoice.getDate().toInstant(), ZoneId.systemDefault()));

        numberField = new TextField();
        numberField.setLabel("номер накладной");
        numberField.setSizeFull();
        numberField.setValue(String.valueOf(invoice.getNumber()));

        isInStockField = new TextField();
        isInStockField.setLabel("Документы в наличии");
        isInStockField.setSizeFull();
        if (invoice.isInStock()) {
            isInStockField.setValue("Да");
        } else {
            isInStockField.setValue("Нет");
        }

        add(dateField);
        add(numberField);
        add(isInStockField);
        add(makeProductMenu());
    }

    private VerticalLayout makeProductMenu() {
        VerticalLayout productMenu = new VerticalLayout();
        invoice.getInvoiceProductList().forEach(prod -> {
            TextField product =  new TextField("Название");
            product.setValue(prod.getProduct().getName());

            TextField count =  new TextField("Кол-во");
            count.setValue(String.valueOf(prod.getCount()));

            productMenu.add(new HorizontalLayout(product, count));
        });
        return productMenu;
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(InvoiceViewAll.class);
        });
        return backButton;
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            invoice.setNumber(Long.parseLong(numberField.getValue()));
            invoice.setDate(Date.from(Instant.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            invoice.setInStock(Boolean.parseBoolean(isInStockField.getValue()));
            invoicePresenter.save(invoice);
            UI.getCurrent().navigate(InvoiceViewAll.class);
        });
        return saveButton;
    }
}
