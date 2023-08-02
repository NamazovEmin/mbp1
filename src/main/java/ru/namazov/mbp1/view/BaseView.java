/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class BaseView extends AppLayout {

    private final List<Component> routerLinkList = new ArrayList<>();

    public BaseView() {
        createHeader();
        createDrawer();
        createMain();
    }

    private void createMain() {

    }

    private void createHeader() {
        H1 logo = new H1("Namazov Emin CRM for meat business");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);
        HorizontalLayout horizontalLayout = new HorizontalLayout(new DrawerToggle(), logo);
        horizontalLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        horizontalLayout.expand(logo);
        horizontalLayout.setWidthFull();
        horizontalLayout.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(horizontalLayout);
    }

    private void createDrawer() {
        initRightDrawerBarLinks();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(routerLinkList);
        addToDrawer(verticalLayout);
    }

    private void initRightDrawerBarLinks() {
        RouterLink clientsLink = new RouterLink("Клиенты", ClientsView.class);
        RouterLink orderLink = new RouterLink("Склад", StorageView.class);
        routerLinkList.add(clientsLink);
        routerLinkList.add(orderLink);
    }
}
