/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.springframework.stereotype.Component;

@Component
@Route("manager")
public class ManagerView extends VerticalLayout {

    private Button newOrder;

    public ManagerView() {
        this.newOrder = new Button("Создать новый заказ");
        newOrder.setId("1");
        add(newOrder);
    }
}
