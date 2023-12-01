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

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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

@Route(value = "/admin/bookkeeping/invoice/read", layout = MainView.class)
@PermitAll
public class InvoiceView extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Invoice invoice;
    private InvoicePresenter invoicePresenter;
    private TextField dateField;
    private TextField numberField;
    private TextField isInStockField;
    private TextField isPaid;
    private TextField isReceived;
    private TextField telNumberField;

    public InvoiceView(InvoicePresenter invoicePresenter) {
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

        dateField = new TextField();
        dateField.setLabel("Дата");
        dateField.setSizeFull();
        dateField.setValue(invoice.getDate().toString());
        dateField.setReadOnly(true);

        numberField = new TextField();
        numberField.setLabel("номер накладной");
        numberField.setSizeFull();
        numberField.setValue(String.valueOf(invoice.getNumber()));
        numberField.setReadOnly(true);

        isInStockField = new TextField();
        isInStockField.setLabel("Документы в наличии");
        isInStockField.setSizeFull();
        if (invoice.isInStock()) {
            isInStockField.setValue("Да");
        } else {
            isInStockField.setValue("Нет");
        }
        isInStockField.setReadOnly(true);

        isPaid = new TextField();
        isPaid.setLabel("Накладная оплачена?");
        isPaid.setSizeFull();
        if (invoice.isPaid()) {
            isPaid.setValue("Да");
        } else {
            isPaid.setValue("Нет");
        }
        isPaid.setReadOnly(true);

        isReceived = new TextField();
        isReceived.setLabel("Товар получен?");
        isReceived.setSizeFull();
        if (invoice.isReceived()) {
            isReceived.setValue("Да");
        } else {
            isReceived.setValue("Нет");
        }
        isReceived.setReadOnly(true);



        add(dateField);
        add(numberField);
        add(isInStockField);
        add(isPaid);
        add(isReceived);
        add(makeProductMenu());
    }

    private VerticalLayout makeProductMenu() {
        VerticalLayout productMenu = new VerticalLayout();
        invoice.getInvoiceProductList().forEach(prod -> {
            TextField product =  new TextField("Название");
            product.setValue(prod.getProduct().getName());
            product.setReadOnly(true);

            TextField count =  new TextField("Кол-во");
            count.setValue(String.valueOf(prod.getCount()));
            count.setReadOnly(true);

            productMenu.add(new HorizontalLayout(product, count));
        });
        return productMenu;
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton()));
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(InvoiceViewAll.class);
        });
        return backButton;
    }
}
