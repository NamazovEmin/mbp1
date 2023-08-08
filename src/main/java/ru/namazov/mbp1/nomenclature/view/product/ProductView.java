/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

package ru.namazov.mbp1.nomenclature.view.product;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import ru.namazov.mbp1.base.ViewConstructor;
import ru.namazov.mbp1.nomenclature.model.Product;
import ru.namazov.mbp1.nomenclature.presenter.ProductPresenter;
import ru.namazov.mbp1.view.MainView;

import jakarta.annotation.security.RolesAllowed;

@Route(value = "/admin/nomenclature/products", layout = MainView.class)
@RolesAllowed("ADMIN")
public class ProductView extends VerticalLayout implements ViewConstructor {

    private final ProductPresenter productPresenter;
    private final Grid<Product> table = new Grid<>(Product.class, false);


    public ProductView(ProductPresenter productPresenter) {
        this.productPresenter = productPresenter;
        header();
        main();
        footer();
    }

    @Override
    public void header() {
        TextField nameField = new TextField();
        nameField.setValue("Список Номенклатуры продуктов");
        nameField.setSizeFull();
        nameField.setReadOnly(true);
        add(nameField);
    }

    @Override
    public void main() {
        table.addColumn(Product::getName).setHeader("Название").setResizable(true);
        table.addColumn(Product::getMeasurement).setHeader("Единица измерения").setResizable(true);
        table.addColumn(Product::getDescription).setHeader("Описание товара").setResizable(true);
        table.setItems(productPresenter.findAll());
        add(table);
    }

    @Override
    public void footer() {
        add(new HorizontalLayout(addButton(), editButton()));
    }

    private Button editButton() {
        var editButton = new Button("Редактировать");
        editButton.addSingleClickListener(click -> {
            Product product = table.asSingleSelect().getValue();
            if (product != null) {
                UI.getCurrent().navigate(ProductEditView.class, product.getId());
            }
        });
        return editButton;
    }

    private Button addButton() {
        var createButton = new Button("Добавить");
        createButton.addSingleClickListener(click -> UI.getCurrent().navigate(ProductAddView.class));
        return createButton;
    }
}
