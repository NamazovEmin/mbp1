/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.order.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.base.view.MainView;
import ru.namazov.mbp1.order.entity.Order;
import ru.namazov.mbp1.order.presenter.OrderPresenter;

import jakarta.annotation.security.PermitAll;

@Route(value = "order", layout = MainView.class)
@PermitAll
public class OrderViewAll extends VerticalLayout implements ViewConstructor {

    private final OrderPresenter orderPresenter;
    private final Grid<Order> table = new Grid<>(Order.class, false);

    public OrderViewAll(OrderPresenter orderPresenter) {
        this.orderPresenter = orderPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        TextField nameField = new TextField();
        nameField.setValue("Список Заказов");
        nameField.setSizeFull();
        nameField.setReadOnly(true);
        add(nameField);
    }

    @Override
    public void main() {
        table.addColumn(Order::getDate).setHeader("Дата").setResizable(true);
        table.addColumn(Order::getTimeFrom).setHeader("с ").setResizable(true);
        table.addColumn(Order::getTimeTo).setHeader("по ").setResizable(true);
        table.addColumn(Order::getAddress).setHeader("Адрес доставки").setResizable(true);
        table.addColumn(Order::getContact).setHeader("Контактное лицо").setResizable(true);
        table.addColumn(Order::getTelNumber).setHeader("Телефон").setResizable(true);
        table.setItems(orderPresenter.findAll());
        add(table);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(addButton(), editButton(), readButton()));
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(OrderViewAdd.class));
        return createButton;
    }

    private Button editButton() {
        var editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            Order order = table.asSingleSelect().getValue();
            if (order != null) {
                UI.getCurrent().navigate(OrderViewEdit.class, order.getId());
            }
        });
        return editButton;
    }

    private Button readButton() {
        var readButton = new Button("Просмотреть");
        readButton.addSingleClickListener(click -> {
            Order client = table.asSingleSelect().getValue();
            if (client != null) {
                UI.getCurrent().navigate(OrderView.class, client.getId());
            }
        });
        return readButton;
    }
}
