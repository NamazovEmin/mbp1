/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.view;

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
import ru.namazov.mbp1.client.presenter.ClientPresenter;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.presenter.OrderPresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "order/edit", layout = MainView.class)
@PermitAll
public class OrderViewEdit extends VerticalLayout implements HasUrlParameter<Long>, ViewConstructor {

    private Order order;
    private final OrderPresenter orderPresenter;
    private final ClientPresenter clientPresenter;
    private TextField clientField;
    private TextField timeFromField;
    private TextField timeToField;
    private TextField addressField;
    private TextField contactMenField;
    private TextField telNumberField;

    public OrderViewEdit(OrderPresenter orderPresenter, ClientPresenter clientPresenter) {
        this.orderPresenter = orderPresenter;
        this.clientPresenter = clientPresenter;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        if (id != null) {
            order = orderPresenter.findById(id);
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

        clientField = new TextField();
        clientField.setLabel("Название Фирмы");
        clientField.setSizeFull();
        clientField.setValue(order.getClient().getName());
        clientField.setReadOnly(true);

        timeFromField = new TextField();
        timeFromField.setLabel("c ");
        timeFromField.setSizeFull();
        timeFromField.setValue(order.getTimeFrom().toString());

        timeToField = new TextField();
        timeToField.setLabel("по");
        timeToField.setSizeFull();
        timeToField.setValue(order.getTimeTo().toString());

        addressField = new TextField();
        addressField.setLabel("Адрес");
        addressField.setSizeFull();
        addressField.setValue(order.getAddress());


        contactMenField = new TextField();
        contactMenField.setLabel("Контактное лицо");
        contactMenField.setSizeFull();
        contactMenField.setValue(order.getContact());

        telNumberField = new TextField();
        telNumberField.setLabel("Телефон");
        telNumberField.setSizeFull();
        telNumberField.setValue(order.getTelNumber());

        add(clientField);
        add(timeFromField);
        add(timeToField);
        add(addressField);
        add(contactMenField);
        add(telNumberField);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(backButton(), saveButton()));
    }

    private Button saveButton() {
        Button saveButton = new Button("Сохранить");
        saveButton.addSingleClickListener(click -> {
//            order.setTimeFrom(Long.parseLong(timeFromField.getValue()));
//            order.setTimeTo(Long.parseLong(timeToField.getValue()));
//            order.setAddress(addressField.getValue());
//            order.setContact(contactMenField.getValue());
//            order.setTelNumber(telNumberField.getValue());
//            orderPresenter.save(order);
//            UI.getCurrent().navigate(OrderViewAll.class);
        });

        return saveButton;
    }

    private Button backButton() {
        Button backButton = new Button("Назад");
        backButton.addSingleClickListener(click -> {
            UI.getCurrent().navigate(OrderViewAll.class);
        });
        return backButton;
    }
}
