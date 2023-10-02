/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.base.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

import ru.namazov.mbp1.bookkeeping.view.BookkeepingView;
import ru.namazov.mbp1.client.view.ClientViewAll;
import ru.namazov.mbp1.login.presenter.LoginPresenter;
import ru.namazov.mbp1.nomenclature.view.NomenclatureView;
import ru.namazov.mbp1.order.view.OrderViewAll;
import ru.namazov.mbp1.storage.view.StorageViewAll;

import jakarta.annotation.security.PermitAll;

@Route("")
@PermitAll
public class MainView extends AppLayout {

    private final List<Component> routerLinkList = new ArrayList<>();
    private final LoginPresenter loginPresenter;

    public MainView(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        if (loginPresenter.getRole()) {
            initAdmin();
        } else {
            initUser();
        }
        createHeader();
        createDrawer();
        createMain();
    }

    private void createMain() {

    }

    private void createHeader() {
        H1 logo = new H1("JAVA CRM");
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
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(routerLinkList);
        addToDrawer(verticalLayout);
    }

    private void initAdmin() {
        RouterLink orderViewLink = new RouterLink("Заказы", OrderViewAll.class);
        RouterLink clientsLink = new RouterLink("Клиенты", ClientViewAll.class);
        RouterLink orderLink = new RouterLink("Склад", StorageViewAll.class);
        RouterLink nomenclatureViewLink = new RouterLink("Номенклатура", NomenclatureView.class);
        RouterLink bookkeepingViewLink = new RouterLink("Бухгалтерия", BookkeepingView.class);

        routerLinkList.add(orderViewLink);
        routerLinkList.add(clientsLink);
        routerLinkList.add(orderLink);
        routerLinkList.add(nomenclatureViewLink);
        routerLinkList.add(bookkeepingViewLink);
    }

    private void initUser() {
        RouterLink clientsLink = new RouterLink("Клиенты", ClientViewAll.class);
        routerLinkList.add(clientsLink);

    }

}
