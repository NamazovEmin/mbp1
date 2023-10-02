/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.bookkeeping.view;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.bookkeeping.model.Invoice;
import ru.namazov.mbp1.bookkeeping.model.InvoiceProduct;
import ru.namazov.mbp1.bookkeeping.presenter.InvoicePresenter;
import ru.namazov.mbp1.bookkeeping.presenter.InvoiceProductPresenter;
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.order.view.OrderViewAll;
import ru.namazov.mbp1.validation.Validation;

import jakarta.annotation.security.PermitAll;

@Route(value = "/admin/bookkeeping/invoice/add", layout = MainView.class)
@PermitAll
public class InvoiceViewAdd extends VerticalLayout implements ViewConstructor {

    private final Validation validation;
    private final InvoicePresenter invoicePresenter;
    private final ClientPresenter clientPresenter;
    private final ProductPresenter productPresenter;
    private final InvoiceProductPresenter invoiceProductPresenter;
    private VerticalLayout productMenu = new VerticalLayout();
    private DatePicker date;
    private TextField number;
    private ComboBox<Product> product;
    private TextField isInStock;
    private TextField count;
    private List<? extends Component> components;
    private Binder<Invoice> invoiceBinder = new BeanValidationBinder<>(Invoice.class);
    HorizontalLayout horizontalLayout;

    public InvoiceViewAdd(Validation validation, InvoicePresenter invoicePresenter, ClientPresenter clientPresenter,
            ProductPresenter productPresenter, InvoiceProductPresenter invoiceProductPresenter) {
        this.validation = validation;
        this.invoicePresenter = invoicePresenter;
        this.clientPresenter = clientPresenter;
        this.productPresenter = productPresenter;
        this.invoiceProductPresenter = invoiceProductPresenter;
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
        number = new TextField("Номер Накладной");
        number.setErrorMessage("Введите номер накладной");

        date = new DatePicker("Дата");
        date.setValue(LocalDate.now().plusDays(1));
        date.setErrorMessage("Выберите дату формирования накладной");

        isInStock = new TextField("Документы в наличии?");
        isInStock.setSizeFull();
        isInStock.setErrorMessage("Введите Адрес доставки");

        product = new ComboBox<>("Название Продукта");
        product.setItems(productPresenter.findAll());
        product.setItemLabelGenerator(Product::getName);
        product.setErrorMessage("Выберите название продукта из списка");

        count = new TextField("шт");
        count.setErrorMessage("Введите кол-во товара");

        Button delButton = new Button("-");
        delButton.addSingleClickListener(click -> {
            if (delButton.getParent().isPresent()) {
                delButton.getParent().get().setVisible(false);
            }
        });

        Button addButton = new Button("+");
        buttonClick(addButton);

        add(number);
        add(date);
        add(isInStock);
        add(productMenu);
        productMenu.add(new HorizontalLayout(product, count, delButton, addButton));
        invoiceBinder.bindInstanceFields(this);
        components = List.of(
                number,
                date,
                isInStock
        );
    }


    private void buttonClick(Button button) {
        button.addSingleClickListener(click -> {
            productMenu.getChildren().forEach(hor -> {
                hor.getChildren().forEach(component -> {
                    if (component instanceof  Button but && but.getText().equals("+")) {
                        but.setVisible(false);
                    }
                });
            });


            ComboBox<Product> addProduct = new ComboBox<>("Название Продукта");
            addProduct.setItems(productPresenter.findAll());
            addProduct.setItemLabelGenerator(Product::getName);
            addProduct.setErrorMessage("Выберите название продукта из списка");

            TextField addCount = new TextField("шт");
            addCount.setErrorMessage("Введите кол-во товара");

            Button delBut = new Button("-");
            delBut.addSingleClickListener(clck -> {
                if (delBut.getParent().isPresent()) {
                    delBut.getParent().get().setVisible(false);
                }
            });

            Button addBut = new Button("+");
            addBut.addSingleClickListener(lick -> buttonClick(addBut));
            productMenu.add(new HorizontalLayout(addProduct, addCount, delBut, addBut));
        });
    }

    @Override
    public void footer() {
        horizontalLayout = new HorizontalLayout(backButton(), saveButton());
        add(horizontalLayout);
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
            if (invoiceBinder.isValid()) {
                List<InvoiceProduct> productList = new ArrayList<>();
                Invoice invoice = new Invoice();
                invoice.setNumber(Long.parseLong(number.getValue()));
                invoice.setDate(Date.from(Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
                invoice.setInStock(Boolean.parseBoolean(isInStock.getValue()));
                productMenu.getChildren().forEach(line -> {
                    InvoiceProduct invoiceProduct = new InvoiceProduct();
                    line.getChildren().forEach(element -> {
                        if (element instanceof ComboBox<?> prod) {
                            invoiceProduct.setProduct((Product) prod.getValue());
                        }
                        if (element instanceof TextField textField) {
                            invoiceProduct.setCount(Long.parseLong(textField.getValue()));
                        }
                        productList.add(invoiceProduct);
                    });
                });
                invoice.setInvoiceProductList(productList);
                Invoice savedInvoice = invoicePresenter.save(invoice);
                productList.forEach(i -> {
                    i.setInvoice(savedInvoice);
                    invoiceProductPresenter.save(i);
                });
                UI.getCurrent().navigate(InvoiceViewAll.class);
            } else {
                BinderValidationStatus<Invoice> validate = invoiceBinder.validate();
                validate.getBeanValidationErrors().forEach(ValidationResult::getErrorMessage);
            }
        });
        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> UI.getCurrent().navigate(OrderViewAll.class));
        return backButton;
    }
}
